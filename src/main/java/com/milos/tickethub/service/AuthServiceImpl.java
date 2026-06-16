package com.milos.tickethub.service;

import com.milos.tickethub.dto.LoginRequest;
import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;
import com.milos.tickethub.entity.Role;
import com.milos.tickethub.entity.User;
import com.milos.tickethub.exception.EmailAlreadyExistsException;
import com.milos.tickethub.mapper.UserMapper;
import com.milos.tickethub.repository.UserRepository;
import com.milos.tickethub.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponse registerUser(RegisterRequest request){
        if (userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException(request.email());
        }
        User user = UserMapper.toEntity(request);
        user.setRoles(Set.of(Role.USER));
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    @Override
    public String loginUser(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        UserDetails user = (UserDetails) authentication.getPrincipal();
        assert user != null;
        return jwtUtil.generateToken(user.getUsername());
    }
}
