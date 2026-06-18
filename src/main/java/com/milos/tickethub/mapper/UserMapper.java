package com.milos.tickethub.mapper;

import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;
import com.milos.tickethub.entity.User;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles()
        );
    }
    public static User toEntity(RegisterRequest request) {
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
}
