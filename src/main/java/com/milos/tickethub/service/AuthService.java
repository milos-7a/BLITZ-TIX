package com.milos.tickethub.service;

import com.milos.tickethub.dto.LoginRequest;
import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;

public interface AuthService {
    UserResponse registerUser(RegisterRequest request);
    String loginUser(LoginRequest request);

}
