package com.example.productCatelog.service;

import com.example.productCatelog.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto createUser(UserDto userDto);

    Page<UserDto> getUsers(Pageable pageable);
}
