package com.milos.tickethub.service;

import com.milos.tickethub.dto.LoginRequest;
import com.milos.tickethub.dto.LoginResponse;
import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;

import java.util.List;

public interface UserService {

    public UserResponse registerUser(RegisterRequest request);
    public LoginResponse loginUser(LoginRequest request);

}
