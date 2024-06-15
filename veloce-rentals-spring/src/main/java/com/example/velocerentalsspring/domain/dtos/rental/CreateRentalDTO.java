package com.example.velocerentalsspring.domain.dtos.rental;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record CreateRentalDTO(
        @NotNull(message = "User ID is required")
        Long userId,
        @NotNull(message = "Vehicle ID is required")
        Long vehicleId,
        @NotNull(message = "Start date is required")
        LocalDate startDate,
        @NotNull(message = "End date is required")
        LocalDate endDate
) {
}
