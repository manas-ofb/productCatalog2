package com.example.productCatelog.controller;

import com.example.productCatelog.dto.UserDto;
import com.example.productCatelog.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDto createProduct(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/list")
    public Page<UserDto> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }
}
