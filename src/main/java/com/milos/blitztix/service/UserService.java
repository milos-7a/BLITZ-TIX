package com.milos.blitztix.service;

import com.milos.blitztix.dto.ChangeRoleRequest;
import com.milos.blitztix.dto.PasswordChangeRequest;
import com.milos.blitztix.dto.UpdateUserRequest;
import com.milos.blitztix.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getCurrentUser();
    UserResponse updateCurrentUser(UpdateUserRequest request);
    String changePasswordForCU(PasswordChangeRequest request);
    UserResponse getUserByID(Long id);
    List<UserResponse> getAllUsers();
    UserResponse changeUserRole(Long id, ChangeRoleRequest request);
}
