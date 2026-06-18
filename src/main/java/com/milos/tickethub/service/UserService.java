package com.milos.tickethub.service;

import com.milos.tickethub.dto.ChangeRoleRequest;
import com.milos.tickethub.dto.PasswordChangeRequest;
import com.milos.tickethub.dto.UpdateUserRequest;
import com.milos.tickethub.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getCurrentUser();
    UserResponse updateCurrentUser(UpdateUserRequest request);
    String changePasswordForCU(PasswordChangeRequest request);
    UserResponse getUserByID(Long id);
    List<UserResponse> getAllUsers();
    UserResponse changeUserRole(Long id, ChangeRoleRequest request);
}
