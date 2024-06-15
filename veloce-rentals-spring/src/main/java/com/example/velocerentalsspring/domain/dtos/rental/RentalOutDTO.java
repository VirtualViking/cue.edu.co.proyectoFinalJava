package com.example.velocerentalsspring.domain.dtos.rental;

import com.example.velocerentalsspring.domain.dtos.user.UserOutDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.VehicleOutDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record RentalOutDTO(
        Long id,
        UserOutDTO user,
        VehicleOutDTO vehicle,
        LocalDate startDate,
        LocalDate endDate,
        Double totalPrice
) {
}
