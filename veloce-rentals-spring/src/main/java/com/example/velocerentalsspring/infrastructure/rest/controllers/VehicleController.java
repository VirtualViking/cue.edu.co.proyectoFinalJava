package com.example.velocerentalsspring.infrastructure.rest.controllers;

import com.example.velocerentalsspring.application.services.VehicleService;
import com.example.velocerentalsspring.domain.dtos.vehicle.CreateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.UpdateVehicleDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.VehicleOutDTO;
import com.example.velocerentalsspring.infrastructure.exceptions.VehicleException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
  private final VehicleService service;

  @PostMapping("/create")
  public ResponseEntity<VehicleOutDTO> createVehicle(@Valid @RequestBody CreateVehicleDTO vehicle) {
    return new ResponseEntity<>(
            service.save(vehicle),
            HttpStatus.CREATED);
  }

  @GetMapping("")
  public ResponseEntity<List<VehicleOutDTO>> getAllVehicles() {
    return new ResponseEntity<>(
            service.getAll(),
            HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<VehicleOutDTO> getVehicleById(@PathVariable Long id) {
    Optional<VehicleOutDTO> vehicle = service.getById(id);
    return vehicle.map((v) -> new ResponseEntity<>(v, HttpStatus.OK))
            .orElseThrow(()-> new VehicleException(HttpStatus.NOT_FOUND, "The vehicle with id " + id + " was not found."));
  }

  @PutMapping("/update")
  public ResponseEntity<VehicleOutDTO> updateVehicle(@Valid @RequestBody UpdateVehicleDTO vehicleDTO){
    Optional<VehicleOutDTO> vehicleUpdated = service.update(vehicleDTO);

    return vehicleUpdated.map((v) -> new ResponseEntity<>(v, HttpStatus.OK))
            .orElseThrow(()-> new VehicleException(HttpStatus.NOT_FOUND, "The vehicle with id " + vehicleDTO.id() + " was not found."));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteVehicle(@PathVariable Long id){
    Optional<VehicleOutDTO> vehicle = service.getById(id);
    if(vehicle.isEmpty()){
      throw new VehicleException(HttpStatus.NOT_FOUND, "The vehicle with id " + id + " was not found.");
    }

    service.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
