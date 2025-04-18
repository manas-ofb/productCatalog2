package com.example.productCatelog.dto;

import com.example.productCatelog.Enum.Permission;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String number;

    private String otpCode;

    private Set<Permission> permissions;
}
