package com.example.velocerentalsspring.application.services;

import com.example.velocerentalsspring.application.mappers.imp.UserMapperImp;
import com.example.velocerentalsspring.application.services.imp.AuthServiceImp;
import com.example.velocerentalsspring.application.services.imp.UserServiceImp;
import com.example.velocerentalsspring.domain.dtos.user.CreateUserDTO;
import com.example.velocerentalsspring.domain.dtos.user.UserOutDTO;
import com.example.velocerentalsspring.domain.models.User;
import com.example.velocerentalsspring.infrastructure.adapters.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  private UserRepository repository;

  @Mock
  private UserMapperImp mapper;

  @Mock
  private AuthServiceImp authService;

  @InjectMocks
  private UserServiceImp userService;

  private CreateUserDTO createUserDTO;
  private User user;
  private User userToSave;
  private User userSaved;
  private UserOutDTO userOutDTO;

  @BeforeEach
  public void setUp() {
    // Initialize the mock return values
    createUserDTO = mock(CreateUserDTO.class);
    user = mock(User.class);
    userToSave = mock(User.class);
    userSaved = mock(User.class);
    userOutDTO = mock(UserOutDTO.class);

    when(createUserDTO.email()).thenReturn("user@example.com");
    when(createUserDTO.password()).thenReturn("password");

    when(mapper.toEntity(any(CreateUserDTO.class))).thenReturn(userToSave);
    when(authService.encodePassword(anyString())).thenReturn("encodedPassword");
    when(repository.save(any(User.class))).thenReturn(userSaved);
    when(mapper.toDTO(any(User.class))).thenReturn(userOutDTO);
  }

  @Test
  public void testSaveUserSuccessfully() {
    when(repository.findByEmail(createUserDTO.email())).thenReturn(Optional.empty());

    UserOutDTO result = userService.save(createUserDTO);

    verify(repository).findByEmail(createUserDTO.email());
    verify(mapper).toEntity(createUserDTO);
    verify(authService).encodePassword(createUserDTO.password());
    verify(repository).save(userToSave);
    verify(mapper).toDTO(userSaved);

    assertNotNull(result);
    assertEquals(userOutDTO, result);
  }

}
