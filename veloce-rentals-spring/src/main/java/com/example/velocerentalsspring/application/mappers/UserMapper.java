package com.example.velocerentalsspring.application.mappers;

import com.example.velocerentalsspring.domain.dtos.user.CreateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UserOutDTO;
import com.example.velocerentalsspring.domain.models.User;

import java.util.List;

public interface UserMapper {
  UserOutDTO toDTO(User user);
  User toEntity(CreateUserDTO user);
  User toEntity(UserOutDTO user);
  List<UserOutDTO> toDTO(List<User> users);

}
