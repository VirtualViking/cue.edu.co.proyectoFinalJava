package com.example.velocerentalsspring.infrastructure.adapters.repositories;

import com.example.velocerentalsspring.domain.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
  List<Rental> findAllByUser_Id(Long userId);
  List<Rental> findAllByStartDate(LocalDate startDate);
  Optional<Rental> findByVehicle_IdAndStartDateBetween(Long vehicleId, LocalDate startDate, LocalDate endDate);
  Optional<Rental> findByVehicle_IdAndEndDateBetween(Long vehicleId, LocalDate startDate, LocalDate endDate);
}
