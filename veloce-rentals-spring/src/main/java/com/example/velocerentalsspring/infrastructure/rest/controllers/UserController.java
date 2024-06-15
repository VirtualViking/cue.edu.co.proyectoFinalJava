package com.example.velocerentalsspring.infrastructure.rest.controllers;

import com.example.velocerentalsspring.application.services.UserService;
import com.example.velocerentalsspring.domain.dtos.user.CreateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UpdateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UserOutDTO;
import com.example.velocerentalsspring.infrastructure.exceptions.UserException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/create")
  public ResponseEntity<UserOutDTO> create(@Valid @RequestBody CreateUserDTO user) {
    UserOutDTO userSaved = userService.save(user);

    return new ResponseEntity<>(
      userSaved,
      HttpStatus.CREATED
    );
  }

  @GetMapping("")
  public ResponseEntity<List<UserOutDTO>> getAll() {
    List<UserOutDTO> users = userService.getAll();

    return new ResponseEntity<>(
      users,
      HttpStatus.OK
    );
  }
  @GetMapping("/{id}")
  public ResponseEntity<UserOutDTO> getById(@PathVariable Long id) {
    Optional<UserOutDTO> userOptional = userService.getById(id);

    return userOptional
            .map((u)-> new ResponseEntity<>(u, HttpStatus.OK))
            .orElseThrow(()-> new UserException(HttpStatus.NOT_FOUND, "The user with id " + id + " was not found."));
  }

  @PutMapping("/update")
  public ResponseEntity<UserOutDTO> update(@Valid @RequestBody UpdateUserDTO userDTO){
    Optional<UserOutDTO> userUpdated = userService.update(userDTO);

    return userUpdated
            .map((u)-> new ResponseEntity<>(u, HttpStatus.OK))
            .orElseThrow(()-> new UserException(HttpStatus.NOT_FOUND, "The user with id " + userDTO.id() + " was not found."));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    userService.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
