package com.example.velocerentalsspring.application.services.imp;

import com.example.velocerentalsspring.application.mappers.VehicleMapper;
import com.example.velocerentalsspring.application.services.VehicleService;
import com.example.velocerentalsspring.domain.dtos.vehicle.CreateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.UpdateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.VehicleOutDTO;
import com.example.velocerentalsspring.domain.enums.VehicleStatus;
import com.example.velocerentalsspring.domain.models.Vehicle;
import com.example.velocerentalsspring.infrastructure.adapters.repositories.VehicleRepository;
import com.example.velocerentalsspring.infrastructure.exceptions.VehicleException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VehicleServiceImp implements VehicleService {
  private final VehicleRepository repository;
  private final VehicleMapper mapper;
  @Override
  public VehicleOutDTO save(CreateVehicleDTO vehicle) {
    Vehicle vehicleToSave = mapper.toEntity(vehicle);
    vehicleToSave.setStatus(VehicleStatus.AVAILABLE);

    Vehicle vehicleSaved = repository.save(vehicleToSave);

    return mapper.toDTO(vehicleSaved);
  }

  @Override
  public List<VehicleOutDTO> getAll() {
    return mapper.toDTO(repository.findAll());
  }

  @Override
  public Optional<VehicleOutDTO> getById(Long id) {
    return repository.findById(id).map(mapper::toDTO);
  }

  @Override
  public Optional<VehicleOutDTO> update(UpdateVehicleDTO vehicle) {
    Optional<Vehicle> optionalVehicle = repository.findById(vehicle.id());

    if(optionalVehicle.isEmpty()) {
      return Optional.empty();
    }

    Vehicle vehicleToUpdate = optionalVehicle.get();

    if(vehicle.brand() != null) {
      vehicleToUpdate.setBrand(vehicle.brand());
    }
    if(vehicle.model() != null) {
      vehicleToUpdate.setModel(vehicle.model());
    }
    if(vehicle.urlImage() != null) {
      vehicleToUpdate.setUrlImage(vehicle.urlImage());
    }
    if(vehicle.rentalPrice() != null) {
      vehicleToUpdate.setRentalPrice(vehicle.rentalPrice());
    }

    Vehicle vehicleUpdated = repository.save(vehicleToUpdate);

    return Optional.of(mapper.toDTO(vehicleUpdated));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  @Override
  public void updateVehicleStatus(Long vehicleId, VehicleStatus status) {
    Vehicle vehicle = repository
            .findById(vehicleId)
            .orElseThrow(()-> new VehicleException(HttpStatus.NOT_FOUND, "Vehicle not found"));

    vehicle.setStatus(status);
    repository.save(vehicle);
  }
}
