package com.example.velocerentalsspring.domain.dtos.rental;

import java.time.LocalDate;

public record UpdateRentalDTO(
        Long id,
        Long userId,
        Long vehicleId,
        LocalDate startDate,
        LocalDate endDate
) {
}
