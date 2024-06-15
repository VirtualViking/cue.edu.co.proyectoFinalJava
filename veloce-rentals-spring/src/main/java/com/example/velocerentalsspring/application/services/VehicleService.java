package com.example.velocerentalsspring.application.services;

import com.example.velocerentalsspring.domain.dtos.vehicle.CreateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.UpdateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.VehicleOutDTO;
import com.example.velocerentalsspring.domain.enums.VehicleStatus;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
  VehicleOutDTO save(CreateVehicleDTO vehicle);
  List<VehicleOutDTO> getAll();
  Optional<VehicleOutDTO> getById(Long id);
  Optional<VehicleOutDTO> update(UpdateVehicleDTO vehicle);
  void deleteById(Long id);

  void updateVehicleStatus(Long vehicleId, VehicleStatus status);
}
