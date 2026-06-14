package com.milos.tickethub.service;

import com.milos.tickethub.dto.LoginRequest;
import com.milos.tickethub.dto.LoginResponse;
import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;
import com.milos.tickethub.entity.User;
import com.milos.tickethub.exception.EmailAlreadyExistsException;
import com.milos.tickethub.mapper.UserMapper;
import com.milos.tickethub.repository.UserRepository;
import com.milos.tickethub.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponse registerUser(RegisterRequest request){
        if (userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException(request.email());
        }
        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponse loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new BadCredentialsException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(request.email());
        UserResponse userResponse = UserMapper.toResponse(user);
        return new LoginResponse(token, userResponse);
    }
}
