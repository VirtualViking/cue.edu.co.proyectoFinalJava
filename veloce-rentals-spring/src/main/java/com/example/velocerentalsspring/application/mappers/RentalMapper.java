package com.example.velocerentalsspring.application.mappers;

import com.example.velocerentalsspring.domain.dtos.rental.CreateRentalDTO;
import com.example.velocerentalsspring.domain.dtos.rental.RentalOutDTO;
import com.example.velocerentalsspring.domain.models.Rental;

import java.util.List;

public interface RentalMapper {
  RentalOutDTO toDTO(Rental rental);
  List<RentalOutDTO> toDTO(List<Rental> rentals);
}
