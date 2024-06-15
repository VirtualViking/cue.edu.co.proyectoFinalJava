package com.example.velocerentalsspring.application.mappers.imp;

import com.example.velocerentalsspring.application.mappers.UserMapper;
import com.example.velocerentalsspring.domain.dtos.user.CreateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UserOutDTO;
import com.example.velocerentalsspring.domain.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImp implements UserMapper {
  @Override
  public UserOutDTO toDTO(User user) {
    return UserOutDTO.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .build();
  }

  @Override
  public User toEntity(CreateUserDTO user) {
    return User.builder()
            .firstName(user.firstName())
            .lastName(user.lastName())
            .email(user.email())
            .password(user.password())
            .build();
  }

  @Override
  public User toEntity(UserOutDTO user) {
    return User.builder()
            .id(user.id())
            .firstName(user.firstName())
            .lastName(user.lastName())
            .email(user.email())
            .build();
  }

  @Override
  public List<UserOutDTO> toDTO(List<User> users) {
    return users.stream()
            .map(this::toDTO)
            .toList();
  }
}
