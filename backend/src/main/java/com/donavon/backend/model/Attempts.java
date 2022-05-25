package com.donavon.backend.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("attempts")
public class Attempts {
  @Id
  private int id;
  private String username;
  private int attempts;
}
