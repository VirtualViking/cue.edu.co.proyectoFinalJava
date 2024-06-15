package com.example.velocerentalsspring.application.services.imp;

import com.example.velocerentalsspring.application.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {
  private final PasswordEncoder passwordEncoder;

  /*
   * Method to encode a password
   * @param password
   * @return String
   */
  @Override
  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
