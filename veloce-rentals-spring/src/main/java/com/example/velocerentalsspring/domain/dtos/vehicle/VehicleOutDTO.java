package com.example.velocerentalsspring.domain.dtos.vehicle;

import com.example.velocerentalsspring.domain.enums.Type;
import com.example.velocerentalsspring.domain.enums.VehicleStatus;
import lombok.Builder;

@Builder
public record VehicleOutDTO(
        Long id,
        String brand,
        String urlImage,
        String model,
        VehicleStatus status,
        Type type,
        Double rentalPrice
) {
}
