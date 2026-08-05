package com.milos.blitztix.service;

import com.milos.blitztix.dto.LoginRequest;
import com.milos.blitztix.dto.LoginResponse;
import com.milos.blitztix.dto.RegisterRequest;
import com.milos.blitztix.dto.UserResponse;

public interface AuthService {
    UserResponse registerUser(RegisterRequest request);
    LoginResponse loginUser(LoginRequest request);

}
