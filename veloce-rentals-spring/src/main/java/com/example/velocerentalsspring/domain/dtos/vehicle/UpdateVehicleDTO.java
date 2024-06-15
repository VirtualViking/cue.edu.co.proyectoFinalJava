package com.example.velocerentalsspring.domain.dtos.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateVehicleDTO(
        @NotBlank(message = "ID is required")
        Long id,
        @NotBlank(message = "Brand is required")
        String brand,
        @NotBlank(message = "URL image is required")
        String urlImage,
        @NotBlank(message = "Model is required")
        String model,
        @NotNull(message = "Type is required")
        Double rentalPrice
) {
}