package com.example.productCatelog.interceptor;

import com.example.productCatelog.Annotation.CheckPermission;
import com.example.productCatelog.Enum.Permission;
import com.example.productCatelog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            // Check if the @CheckPermission annotation is present
            if (method.isAnnotationPresent(CheckPermission.class)) {
                CheckPermission checkPermission = method.getAnnotation(CheckPermission.class);
                Permission requiredPermission = checkPermission.value();

                // Assuming you have a method in UserService to get the user permissions from the token
                String mobileNumber = request.getHeader("Mobile-Number");
                if (mobileNumber == null || !userService.hasPermission(mobileNumber,
                    requiredPermission)) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.getWriter().write("Unauthorized");
                    return false; // Permission check failed, prevent further handling
                }
            }
        }
        return true;
    }
}
