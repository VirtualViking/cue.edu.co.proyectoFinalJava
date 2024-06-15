package com.example.velocerentalsspring.application.mappers.imp;

import com.example.velocerentalsspring.application.mappers.RentalMapper;
import com.example.velocerentalsspring.application.mappers.UserMapper;
import com.example.velocerentalsspring.application.mappers.VehicleMapper;
import com.example.velocerentalsspring.domain.dtos.rental.CreateRentalDTO;
import com.example.velocerentalsspring.domain.dtos.rental.RentalOutDTO;
import com.example.velocerentalsspring.domain.models.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RentalMapperImp implements RentalMapper {
  private final UserMapper userMapper;
  private final VehicleMapper vehicleMapper;

  @Override
  public RentalOutDTO toDTO(Rental rental) {
    return RentalOutDTO.builder()
            .id(rental.getId())
            .user(userMapper.toDTO(rental.getUser()))
            .vehicle(vehicleMapper.toDTO(rental.getVehicle()))
            .startDate(rental.getStartDate())
            .endDate(rental.getEndDate())
            .totalPrice(rental.getTotalPrice())
            .build();
  }


  @Override
  public List<RentalOutDTO> toDTO(List<Rental> rentals) {
    return rentals.stream()
            .map(this::toDTO)
            .toList();
  }
}
