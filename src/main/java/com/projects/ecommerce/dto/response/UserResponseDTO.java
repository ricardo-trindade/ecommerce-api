package com.projects.ecommerce.dto.response;

import com.projects.ecommerce.entity.User;
import com.projects.ecommerce.enums.Roles;

public record UserResponseDTO(
        String name,
        String email,
        String phone,
        Roles role
) {
}
