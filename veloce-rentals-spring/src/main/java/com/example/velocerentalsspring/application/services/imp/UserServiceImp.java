package com.example.velocerentalsspring.application.services.imp;

import com.example.velocerentalsspring.application.mappers.UserMapper;
import com.example.velocerentalsspring.application.services.AuthService;
import com.example.velocerentalsspring.application.services.UserService;
import com.example.velocerentalsspring.domain.dtos.user.CreateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UpdateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UserOutDTO;
import com.example.velocerentalsspring.domain.models.User;
import com.example.velocerentalsspring.infrastructure.adapters.repositories.UserRepository;
import com.example.velocerentalsspring.infrastructure.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
  private UserRepository repository;
  private AuthService authService;
  private UserMapper mapper;
  /**
   * Method to save a user
   * @param user
   * @return UserOutDTO
   */
  @Override
  public UserOutDTO save(CreateUserDTO user) {
    validateUserEmail(user.email());
    User userToSave = mapper.toEntity(user);
    userToSave.setPassword(authService.encodePassword(user.password()));

    User userSaved = repository.save(userToSave);
    return mapper.toDTO(userSaved);
  }

  private void validateUserEmail(String email) {
    Optional<User> userOptional = repository.findByEmail(email);
    if (userOptional.isPresent()) {
      throw new UserException(HttpStatus.BAD_REQUEST, "The email " + email + " is already in use.");
    }
  }
  /**
   * Method to get all users
   * @return List<UserOutDTO>
   */

  @Override
  public List<UserOutDTO> getAll() {
    return mapper.toDTO(
            repository.findAll() // --> List<User>
    );
  }
  /**
   * Method to get a user by id
   * @param id
   * @return Optional<UserOutDTO>
   */

  @Override
  public Optional<UserOutDTO> getById(Long id) {
    return repository
            .findById(id)
            .map(mapper::toDTO);
  }

  /**
   * Method to update a user
   * @param user
   * @return Optional<UserOutDTO>
   */
  @Override
  public Optional<UserOutDTO> update(UpdateUserDTO user) {
    Optional<User> userOptional = repository.findById(user.id());
    if (userOptional.isEmpty()) {
      return Optional.empty();
    }
    User userToUpdate = userOptional.get();

    if(user.firstName() != null) {
      userToUpdate.setFirstName(user.firstName());
    }

    if(user.lastName() != null) {
      userToUpdate.setLastName(user.lastName());
    }

    if(user.password() != null) {
      userToUpdate.setPassword(user.password());
    }

    User userUpdated = repository.save(userToUpdate);

    return Optional.of(
            mapper.toDTO(userUpdated)
    );
  }

  /**
   * Method to delete a user by id
   * @param id
   */
  @Override
  public void deleteById(Long id) {
    Optional<User> userOptional = repository.findById(id);
    if (userOptional.isEmpty()) {
      throw new UserException(HttpStatus.NOT_FOUND,"The user with id " + id + " was not found.");
    }
    repository.deleteById(id);
  }
}
