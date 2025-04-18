package com.example.productCatelog.config;

import com.example.productCatelog.interceptor.AuthenticationInterceptor;
import com.example.productCatelog.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/auth/sendOtp", "/auth/verifyOtp", "/api/user/register",
                "/api/user/list");

        registry.addInterceptor(permissionInterceptor)
            .addPathPatterns("/**") // Apply it globally to all paths
            .excludePathPatterns("/auth/sendOtp", "/auth/verifyOtp", "/api/user/register",
                "/api/user/list");
    }
}
