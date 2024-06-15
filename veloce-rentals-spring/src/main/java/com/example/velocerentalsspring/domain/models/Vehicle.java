package com.example.velocerentalsspring.domain.models;


import com.example.velocerentalsspring.domain.enums.Type;
import com.example.velocerentalsspring.domain.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "vehicles")
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String brand;
  private String urlImage;
  private String model;
  @Enumerated(EnumType.STRING)
  private VehicleStatus status;
  @Enumerated(EnumType.STRING)
  private Type type;
  private Double rentalPrice;
}
