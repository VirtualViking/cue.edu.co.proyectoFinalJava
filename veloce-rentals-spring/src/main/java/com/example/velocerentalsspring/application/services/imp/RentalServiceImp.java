package com.example.velocerentalsspring.application.services.imp;

import com.example.velocerentalsspring.application.mappers.RentalMapper;
import com.example.velocerentalsspring.application.mappers.UserMapper;
import com.example.velocerentalsspring.application.mappers.VehicleMapper;
import com.example.velocerentalsspring.application.services.RentalService;
import com.example.velocerentalsspring.application.services.UserService;
import com.example.velocerentalsspring.application.services.VehicleService;
import com.example.velocerentalsspring.domain.dtos.rental.CreateRentalDTO;
import com.example.velocerentalsspring.domain.dtos.rental.RentalOutDTO;
import com.example.velocerentalsspring.domain.dtos.rental.UpdateRentalDTO;
import com.example.velocerentalsspring.domain.dtos.user.UserOutDTO;
import com.example.velocerentalsspring.domain.dtos.vehicle.VehicleOutDTO;
import com.example.velocerentalsspring.domain.enums.VehicleStatus;
import com.example.velocerentalsspring.domain.models.Rental;
import com.example.velocerentalsspring.infrastructure.adapters.repositories.RentalRepository;
import com.example.velocerentalsspring.infrastructure.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalServiceImp implements RentalService {
  private final RentalRepository rentalRepository;
  private final UserService userService;
  private final RentalMapper mapper;
  private final UserMapper userMapper;
  private final VehicleService vehicleService;
  private final VehicleMapper vehicleMapper;

  /**
   * Method to create a rental
   * @param createRentalDTO
   * @return RentalOutDTO
   */
  @Override
  public RentalOutDTO createRental(CreateRentalDTO createRentalDTO) {
    UserOutDTO user = getUserById(createRentalDTO.userId());

    VehicleOutDTO vehicle = getVehicleById(createRentalDTO.vehicleId());

    validateRentalDates(createRentalDTO);
    validateVehicleAvailability(createRentalDTO);

    Double totalPrice = calculateRentalPrice(vehicle, createRentalDTO.startDate(), createRentalDTO.endDate());

    Rental rental = buildRental(createRentalDTO, user, vehicle, totalPrice);

    Rental rentalSaved = rentalRepository.save(rental);

    return mapper.toDTO(rentalSaved);
  }

  @Override
  public List<RentalOutDTO> findAll() {
    return mapper.toDTO(rentalRepository.findAll());
  }

  @Override
  public List<RentalOutDTO> findAllByUserId(Long userId) {
    return mapper.toDTO(rentalRepository.findAllByUser_Id(userId));
  }

  @Override
  public List<RentalOutDTO> findAllByStartDate(LocalDate startDate) {
    return mapper.toDTO(rentalRepository.findAllByStartDate(startDate));
  }

  @Override
  public Optional<RentalOutDTO> findById(Long id) {
    return rentalRepository.findById(id).map(mapper::toDTO);
  }

  @Override
  public RentalOutDTO updateRental(UpdateRentalDTO updateRentalDTO) {
    Rental rentalToUpdate = rentalRepository.findById(updateRentalDTO.id())
            .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "Rental not found"));

    updateRentalFields(updateRentalDTO, rentalToUpdate);

    return mapper.toDTO(rentalRepository.save(rentalToUpdate));
  }

  @Override
  public void deleteRental(Long id) {
    Rental rental = rentalRepository.findById(id)
              .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "Rental not found"));

      vehicleService.updateVehicleStatus(rental.getVehicle().getId(), VehicleStatus.AVAILABLE);
      rentalRepository.delete(rental);
  }


  private void validateRentalDates(CreateRentalDTO createRentalDTO) {
    if (createRentalDTO.startDate().isAfter(createRentalDTO.endDate())) {
      throw new UserException(HttpStatus.BAD_REQUEST, "Start date must be before end date");
    }
  }
  private void validateVehicleAvailability(CreateRentalDTO rental){
    Optional<Rental> rentalOptional = rentalRepository.findByVehicle_IdAndStartDateBetween(rental.vehicleId(), rental.startDate(), rental.endDate());
    if(rentalOptional.isPresent()){
      throw new UserException(HttpStatus.BAD_REQUEST, "Vehicle is not available for the selected dates");
    }
    rentalOptional = rentalRepository.findByVehicle_IdAndEndDateBetween(rental.vehicleId(), rental.startDate(), rental.endDate());
    if(rentalOptional.isPresent()){
      throw new UserException(HttpStatus.BAD_REQUEST, "Vehicle is not available for the selected dates");
    }

  }

  private UserOutDTO getUserById(Long userId) {
    return userService.getById(userId)
            .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "User not found"));
  }

  private VehicleOutDTO getVehicleById(Long vehicleId) {
    return vehicleService.getById(vehicleId)
            .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "Vehicle not found"));
  }

  private Rental buildRental(CreateRentalDTO createRentalDTO, UserOutDTO user, VehicleOutDTO vehicle, Double totalPrice) {
    return Rental.builder()
            .vehicle(vehicleMapper.toEntity(vehicle))
            .user(userMapper.toEntity(user))
            .startDate(createRentalDTO.startDate())
            .endDate(createRentalDTO.endDate())
            .totalPrice(totalPrice)
            .build();
  }
  private Double calculateRentalPrice(VehicleOutDTO vehicle, LocalDate startDate, LocalDate endDate) {
    Integer days = endDate.getDayOfYear() - startDate.getDayOfYear();
    return vehicle.rentalPrice() * days;
  }

  private void updateRentalFields(UpdateRentalDTO updateRentalDTO, Rental rentalToUpdate) {
    if(updateRentalDTO.startDate() != null){
      rentalToUpdate.setStartDate(updateRentalDTO.startDate());
    }
    if(updateRentalDTO.endDate() != null){
      rentalToUpdate.setEndDate(updateRentalDTO.endDate());
    }
    if(updateRentalDTO.userId() != null){
      UserOutDTO user = getUserById(updateRentalDTO.userId());
      rentalToUpdate.setUser(userMapper.toEntity(user));
    }
    if(updateRentalDTO.vehicleId() != null){
      VehicleOutDTO vehicle = getVehicleById(updateRentalDTO.vehicleId());
      rentalToUpdate.setVehicle(vehicleMapper.toEntity(vehicle));
    }

    if(updateRentalDTO.startDate() != null || updateRentalDTO.endDate() != null){
      Double totalPrice = calculateRentalPrice(vehicleMapper.toDTO(rentalToUpdate.getVehicle()),
              rentalToUpdate.getStartDate(),
              rentalToUpdate.getEndDate());
      rentalToUpdate.setTotalPrice(totalPrice);
    }
  }

}
