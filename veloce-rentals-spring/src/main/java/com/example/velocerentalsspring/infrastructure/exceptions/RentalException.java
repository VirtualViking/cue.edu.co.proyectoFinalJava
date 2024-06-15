package com.example.velocerentalsspring.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class RentalException extends HttpException{
  public RentalException(HttpStatus status, String message) {
    super(status, message);
  }
}
