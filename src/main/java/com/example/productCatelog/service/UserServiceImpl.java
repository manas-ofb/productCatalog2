package com.example.productCatelog.service;

import com.example.productCatelog.Enum.Permission;
import com.example.productCatelog.converter.UserConverter;
import com.example.productCatelog.dto.UserDto;
import com.example.productCatelog.entity.User;
import com.example.productCatelog.repository.UserRepository;
import java.util.Set;
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
        // Check if the user with the same mobile number already exists
        User existingUser = userRepository.findByNumber(userDto.getNumber()).orElse(null);

        if (existingUser != null) {
            // If the user exists, add the required permissions to the existing permissions set
            if (userDto.getPermissions() != null) {
                // Add new permissions to the existing set (no duplicates due to Set)
                existingUser.getPermissions().addAll(userDto.getPermissions());
            }

            // Save the updated user
            existingUser = userRepository.save(existingUser);

            return UserConverter.convertToDTO(existingUser);
        } else {
            // If the user doesn't exist, create a new user
            User user = new User();
            user.setNumber(userDto.getNumber());

            // Set the permissions (use provided permissions or default to READ if none provided)
            if (userDto.getPermissions() == null || userDto.getPermissions().isEmpty()) {
                // If no permissions are provided, default to "READ"
                user.setPermissions(Set.of(Permission.READ));
            } else {
                // Set the permissions provided in the DTO
                user.setPermissions(userDto.getPermissions());
            }

            // Save the new user
            user = userRepository.save(user);

            return UserConverter.convertToDTO(user);
        }
    }

    @Override
    public boolean hasPermission(String mobileNumber, Permission requiredPermission) {
        User user = userRepository.findByNumber(mobileNumber)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getPermissions().contains(requiredPermission);
    }
}
