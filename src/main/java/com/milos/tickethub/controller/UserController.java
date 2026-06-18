package com.milos.tickethub.controller;

import com.milos.tickethub.dto.ChangeRoleRequest;
import com.milos.tickethub.dto.PasswordChangeRequest;
import com.milos.tickethub.dto.UpdateUserRequest;
import com.milos.tickethub.dto.UserResponse;
import com.milos.tickethub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/me")
    public UserResponse getMe(){
        return userService.getCurrentUser();
    }
    @PutMapping("/me")
    public UserResponse updateMe(@RequestBody UpdateUserRequest request){
        return userService.updateCurrentUser(request);
    }
    @PutMapping("/me/password")
    public String updatePassword(@Valid @RequestBody PasswordChangeRequest request){
        return userService.changePasswordForCU(request);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUserByID(id);
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse changeUserRole(@PathVariable Long id, @Valid @RequestBody ChangeRoleRequest request){
        return userService.changeUserRole(id, request);
    }

}
