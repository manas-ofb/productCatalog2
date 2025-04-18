package com.example.productCatelog.Annotation;

import com.example.productCatelog.Enum.Permission;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // This annotation will be used on methods
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime
public @interface CheckPermission {
    Permission value(); // Define the permission type
}
