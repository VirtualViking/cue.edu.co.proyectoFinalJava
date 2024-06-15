package com.example.velocerentalsspring.domain.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        @NotBlank(message = "Id is mandatory")
        Long id,
        @NotBlank(message = "First name is mandatory")
        String firstName,
        @NotBlank(message = "Last name is mandatory")
        String lastName,
        @NotBlank(message = "Password is mandatory")
        String password
) {
}
