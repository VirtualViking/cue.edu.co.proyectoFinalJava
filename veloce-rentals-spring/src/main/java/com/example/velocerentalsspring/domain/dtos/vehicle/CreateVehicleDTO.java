package com.example.velocerentalsspring.domain.dtos.vehicle;

import com.example.velocerentalsspring.domain.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVehicleDTO(
        @NotBlank(message = "Brand is required")
        String brand,
        @NotBlank(message = "URL image is required")
        String urlImage,
        @NotBlank(message = "Model is required")
        String model,
        @NotNull(message = "Type is required")
        Type type,
        @NotNull(message = "Rental price is required")
        Double rentalPrice
) {
}