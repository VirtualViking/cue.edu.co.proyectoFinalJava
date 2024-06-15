package com.example.velocerentalsspring.application.mappers.imp;

import com.example.velocerentalsspring.application.mappers.VehicleMapper;
import com.example.velocerentalsspring.domain.dtos.vehicle.CreateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.UpdateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.VehicleOutDTO;
import com.example.velocerentalsspring.domain.models.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleMapperImp implements VehicleMapper {
  @Override
  public Vehicle toEntity(CreateVehicleDTO vehicle) {
    return Vehicle.builder()
      .brand(vehicle.brand())
      .urlImage(vehicle.urlImage())
      .model(vehicle.model())
      .type(vehicle.type())
      .rentalPrice(vehicle.rentalPrice())
      .build();
  }

  @Override
  public Vehicle toEntity(VehicleOutDTO vehicle) {
    return  Vehicle.builder()
            .id(vehicle.id())
            .brand(vehicle.brand())
            .urlImage(vehicle.urlImage())
            .model(vehicle.model())
            .type(vehicle.type())
            .status(vehicle.status())
            .rentalPrice(vehicle.rentalPrice())
            .build();
  }

  @Override
  public Vehicle toEntity(UpdateVehicleDTO vehicle) {
    return Vehicle.builder()
            .id(vehicle.id())
            .brand(vehicle.brand())
            .urlImage(vehicle.urlImage())
            .model(vehicle.model())
            .rentalPrice(vehicle.rentalPrice())
            .build();
  }

  @Override
  public VehicleOutDTO toDTO(Vehicle vehicle) {
    return VehicleOutDTO.builder()
            .id(vehicle.getId())
            .brand(vehicle.getBrand())
            .urlImage(vehicle.getUrlImage())
            .model(vehicle.getModel())
            .type(vehicle.getType())
            .status(vehicle.getStatus())
            .rentalPrice(vehicle.getRentalPrice())
            .build();
  }

  @Override
  public List<VehicleOutDTO> toDTO(List<Vehicle> vehicles) {
    return vehicles.stream()
            .map(this::toDTO)
            .toList();
  }
}
