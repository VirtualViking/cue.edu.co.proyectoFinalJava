package com.example.velocerentalsspring.application.services;

import com.example.velocerentalsspring.domain.dtos.user.CreateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UpdateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UserOutDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
  UserOutDTO save(CreateUserDTO user);
  List<UserOutDTO> getAll();
  Optional<UserOutDTO> getById(Long id);
  Optional<UserOutDTO> update(UpdateUserDTO user);
  void deleteById(Long id);
}
