package com.example.velocerentalsspring.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class UserException extends HttpException{
  public UserException(HttpStatus status, String message) {
    super(status, message);
  }
}
