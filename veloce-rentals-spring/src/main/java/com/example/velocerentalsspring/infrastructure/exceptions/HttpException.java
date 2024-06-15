package com.example.velocerentalsspring.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class HttpException extends RuntimeException{
  private HttpStatus status;
  private String message;

}
