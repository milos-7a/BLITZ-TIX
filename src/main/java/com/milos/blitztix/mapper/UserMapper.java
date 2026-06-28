package com.milos.blitztix.mapper;

import com.milos.blitztix.dto.RegisterRequest;
import com.milos.blitztix.dto.UserResponse;
import com.milos.blitztix.entity.User;

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
