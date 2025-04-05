package com.example.productCatelog.service;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {

    // 5 mins
    private static final int OTP_EXPIRATION_TIME = 300;

    // 24 hrs
    private static final int TOKEN_EXPIRATION_TIME = 86400;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String generateOtp(String mobileNumber) {
        String otp = generateRandomOtp();
        redisTemplate.opsForValue()
            .set("otp-" + mobileNumber, otp, OTP_EXPIRATION_TIME);
        return otp;
    }

    @Override
    public boolean validateOtp(String mobileNumber, String otp) {
        String storedOtp = redisTemplate.opsForValue().get("otp-" + mobileNumber);
        if (storedOtp == null) {
            return false;
        }
        String trimOtp = storedOtp.substring(storedOtp.length() - 6);
        System.out.println(trimOtp);
        return trimOtp.equals(otp);
    }

    @Override
    public String generateToken(String mobileNumber) {
        String token = "user-token-" + mobileNumber + "-" + System.currentTimeMillis();
        redisTemplate.opsForValue()
            .set("user-token-" + mobileNumber, token,
                TOKEN_EXPIRATION_TIME); // Store token with expiration
        return token;
    }

    private String generateRandomOtp() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        return String.valueOf(otp);
    }
}
