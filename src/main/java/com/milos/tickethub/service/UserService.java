package com.milos.tickethub.service;

import com.milos.tickethub.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User registerUser(User user);

}
