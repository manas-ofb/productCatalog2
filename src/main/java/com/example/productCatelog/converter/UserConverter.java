package com.example.productCatelog.converter;

import com.example.productCatelog.dto.UserDto;
import com.example.productCatelog.entity.User;

public class UserConverter {
    public static UserDto convertToDTO(User user) {
        return new UserDto(user.getId(), user.getNumber(), null, user.getPermissions());
    }
}
