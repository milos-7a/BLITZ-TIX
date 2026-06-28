package com.milos.blitztix.service;

import com.milos.blitztix.dto.ChangeRoleRequest;
import com.milos.blitztix.dto.PasswordChangeRequest;
import com.milos.blitztix.dto.UpdateUserRequest;
import com.milos.blitztix.dto.UserResponse;
import com.milos.blitztix.entity.User;
import com.milos.blitztix.exception.UserNotFoundException;
import com.milos.blitztix.mapper.UserMapper;
import com.milos.blitztix.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserResponse getCurrentUser(){
        User user = getUserFromAuth();
        return UserMapper.toResponse(user);
    }
    public UserResponse updateCurrentUser(UpdateUserRequest request) {
        User user = getUserFromAuth();
        checkUserUpdates(user, request);
        userRepository.save(user);
        return UserMapper.toResponse(user);
    }
    public String changePasswordForCU(PasswordChangeRequest request){
        User user = getUserFromAuth();
        if (checkPassword(user, request)){
            user.setPassword(passwordEncoder.encode(request.newPassword()));
            userRepository.save(user);
        }
        return "Password changed successfully";
    }
    public UserResponse getUserByID(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserMapper.toResponse(user);
    }
    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new RuntimeException("There is no users in the database");
        }
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users){
            UserResponse userResponse = UserMapper.toResponse(user);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }
    public UserResponse changeUserRole(Long id, ChangeRoleRequest request){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (checkIfAdminWantsRoleChange(id)){
            throw new RuntimeException("Admin is not allowed to change his role");
        }
        user.setRoles(new HashSet<>(Set.of(request.role())));
        userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    //Helper methods
    private User getUserFromAuth(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    private void checkUserUpdates(User user, UpdateUserRequest request){
        if (request.firstName() != null && !request.firstName().isBlank()){
            user.setFirstName(request.firstName());
        }
        if (request.lastName() != null && !request.lastName().isBlank()){
            user.setLastName(request.lastName());
        }
    }
    private boolean checkPassword(User user, PasswordChangeRequest request){
        if(request.oldPassword().equals(request.newPassword())){
            throw new BadCredentialsException("New password must be different from old password");
        }
        if(!passwordEncoder.matches(request.oldPassword(),  user.getPassword())){
            throw new BadCredentialsException("Passwords don't match");
        }
        return true;
    }
    private boolean checkIfAdminWantsRoleChange(Long id){
        return id.equals(getUserFromAuth().getId());
    }
}
