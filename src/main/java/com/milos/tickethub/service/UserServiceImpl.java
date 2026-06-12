package com.milos.tickethub.service;

import com.milos.tickethub.entity.User;
import com.milos.tickethub.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user){
        return userRepository.save(user); //changes needed
    }
}
