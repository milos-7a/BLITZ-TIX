package com.milos.blitztix.controller;

import com.milos.blitztix.dto.*;
import com.milos.blitztix.service.TicketService;
import com.milos.blitztix.service.UserService;
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
    private final TicketService ticketService;
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
    @GetMapping("/{id}/tickets")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TicketResponse> getByUsersId(@Valid @PathVariable Long id){
        return ticketService.getTicketsFromUser(id);
    }

}
