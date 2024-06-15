package com.example.velocerentalsspring.infrastructure.rest.controllers;

import com.example.velocerentalsspring.application.services.RentalService;
import com.example.velocerentalsspring.domain.dtos.rental.CreateRentalDTO;
import com.example.velocerentalsspring.domain.dtos.rental.RentalOutDTO;
import com.example.velocerentalsspring.domain.dtos.rental.UpdateRentalDTO;
import com.example.velocerentalsspring.infrastructure.exceptions.RentalException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
  private final RentalService rentalService;

  @PostMapping("/create")
  public ResponseEntity<RentalOutDTO> createRental(@Valid @RequestBody CreateRentalDTO createRentalDTO) {
    return new ResponseEntity<>(
            rentalService.createRental(createRentalDTO),
            HttpStatus.CREATED);
  }

  @GetMapping("")
  public ResponseEntity<List<RentalOutDTO>> findAll() {
    return new ResponseEntity<>(
            rentalService.findAll(),
            HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RentalOutDTO> findById(@PathVariable Long id) {
    return rentalService.findById(id)
            .map(rental-> new ResponseEntity<>(
                            rental,
                            HttpStatus.OK))
            .orElseThrow(() -> new RentalException(HttpStatus.NOT_FOUND,"Rental not found"));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<RentalOutDTO>> findAllByUserId(@PathVariable Long userId) {
    return new ResponseEntity<>(
            rentalService.findAllByUserId(userId),
            HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<RentalOutDTO> updateRental(@Valid @RequestBody UpdateRentalDTO updateRentalDTO) {
    return new ResponseEntity<>(
            rentalService.updateRental(updateRentalDTO),
            HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
    rentalService.deleteRental(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
