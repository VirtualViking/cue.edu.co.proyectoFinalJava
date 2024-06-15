package com.example.velocerentalsspring.domain.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
    @NotBlank(message = "First name is mandatory")
    String firstName,
    @NotBlank(message = "Last name is mandatory")
    String lastName,
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    String email,
    @NotBlank(message = "Password is mandatory")
    String password
) {
}
