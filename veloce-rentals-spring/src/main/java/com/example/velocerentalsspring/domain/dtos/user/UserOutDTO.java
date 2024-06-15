package com.example.velocerentalsspring.domain.dtos.user;

import lombok.Builder;

@Builder
public record UserOutDTO(
    Long id,
    String firstName,
    String lastName,
    String email
) {
}
