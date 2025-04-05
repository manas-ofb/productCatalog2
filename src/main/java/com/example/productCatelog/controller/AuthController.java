package com.example.productCatelog.controller;

import com.example.productCatelog.dto.UserDto;
import com.example.productCatelog.entity.User;
import com.example.productCatelog.repository.UserRepository;
import com.example.productCatelog.service.OtpService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sendOtp")
    public String sendOtp(@RequestBody UserDto userDto) {
        System.out.println(userDto.getNumber());
        Optional<User> optionalUser = userRepository.findByNumber(userDto.getNumber());

        if (optionalUser.isEmpty()) {
            return "User not registered";  // Return response if user is not found
        }

        User user = optionalUser.get();

        String otp = otpService.generateOtp(userDto.getNumber());
        System.out.println("otp: " + otp);

        return "OTP has been sent to your mobile number.";  // Return success message
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestBody UserDto userDto) {
        System.out.println(userDto.getNumber());
        System.out.println(userDto.getOtpCode());
        if (otpService.validateOtp(userDto.getNumber(), userDto.getOtpCode())) {
            String token = otpService.generateToken(userDto.getNumber());
            return "OTP verified successfully. Your token is: " + token;
        } else {
            return "Invalid OTP.";
        }
    }
}
