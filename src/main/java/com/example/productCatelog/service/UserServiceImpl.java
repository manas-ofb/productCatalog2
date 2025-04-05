package com.example.productCatelog.service;

import com.example.productCatelog.converter.UserConverter;
import com.example.productCatelog.dto.UserDto;
import com.example.productCatelog.entity.User;
import com.example.productCatelog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {
        Page<User> Users = userRepository.findAll(pageable);
        return Users.map(UserConverter::convertToDTO);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setNumber(userDto.getNumber());
        userRepository.save(user);

        return UserConverter.convertToDTO(user);
    }
}
