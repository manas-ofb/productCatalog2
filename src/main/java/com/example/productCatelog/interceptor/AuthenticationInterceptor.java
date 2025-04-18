package com.example.productCatelog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        // Retrieve the mobile number and token from request headers
        String mobileNumber = request.getHeader("Mobile-Number");
        String token = request.getHeader("Authorization");

        // Check if both mobileNumber and token are present in headers
        if (mobileNumber == null || token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication Required");
            return false; // Block request
        }

        // Retrieve the stored token from Redis for the given mobile number
        String storedToken = redisTemplate.opsForValue().get("user-token-" + mobileNumber);

        if (storedToken == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or Expired Token");
            return false;
        }
        String trimToken =
            storedToken.substring(storedToken.length() - (mobileNumber.length() + 25));

        System.out.println("storedToken: " + trimToken);
        // Validate the token
        if (!trimToken.equals(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or Expired Token");
            return false; // Block request
        }

        return true;
    }
}
