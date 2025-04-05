package com.example.productCatelog.service;

import org.springframework.stereotype.Service;

@Service
public interface OtpService {

    String generateOtp(String mobileNumber);

    boolean validateOtp(String mobileNumber, String otp);

    String generateToken(String mobileNumber);
}
