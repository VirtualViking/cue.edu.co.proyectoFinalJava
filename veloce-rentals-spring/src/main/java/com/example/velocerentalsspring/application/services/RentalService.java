package com.example.velocerentalsspring.application.services;

import com.example.velocerentalsspring.domain.dtos.rental.CreateRentalDTO;
import com.example.velocerentalsspring.domain.dtos.rental.RentalOutDTO;
import com.example.velocerentalsspring.domain.dtos.rental.UpdateRentalDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalService {

  RentalOutDTO createRental(CreateRentalDTO createRentalDTO);
  List<RentalOutDTO> findAll();
  List<RentalOutDTO> findAllByUserId(Long userId);
  List<RentalOutDTO> findAllByStartDate(LocalDate startDate);
  Optional<RentalOutDTO> findById(Long id);

  RentalOutDTO updateRental(UpdateRentalDTO updateRentalDTO);

  void deleteRental(Long id);
  
}
