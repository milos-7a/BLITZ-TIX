package com.milos.tickethub.service;

import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;
import com.milos.tickethub.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User registerUser(RegisterRequest request);

}
