package com.milos.tickethub.service;

import com.milos.tickethub.dto.LoginRequest;
import com.milos.tickethub.dto.LoginResponse;
import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(RegisterRequest request);
    LoginResponse loginUser(LoginRequest request);

}
