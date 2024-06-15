package com.example.velocerentalsspring.application.mappers;

import com.example.velocerentalsspring.domain.dtos.vehicle.CreateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.UpdateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.VehicleOutDTO;
import com.example.velocerentalsspring.domain.models.Vehicle;

import java.util.List;

public interface VehicleMapper {
  Vehicle toEntity(CreateVehicleDTO vehicle);
  Vehicle toEntity(VehicleOutDTO vehicle);
  Vehicle toEntity(UpdateVehicleDTO vehicle);
  VehicleOutDTO toDTO(Vehicle vehicle);

  List<VehicleOutDTO> toDTO(List<Vehicle> vehicles);
}
