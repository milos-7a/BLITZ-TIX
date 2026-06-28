package com.milos.blitztix.service;

import com.milos.blitztix.dto.LoginRequest;
import com.milos.blitztix.dto.RegisterRequest;
import com.milos.blitztix.dto.UserResponse;

public interface AuthService {
    UserResponse registerUser(RegisterRequest request);
    String loginUser(LoginRequest request);

}
