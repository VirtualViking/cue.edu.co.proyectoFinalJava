package com.example.velocerentalsspring.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class VehicleException extends HttpException{
  public VehicleException(HttpStatus status, String message) {
    super(status, message);
  }
}
