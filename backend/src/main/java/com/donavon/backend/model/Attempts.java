package com.donavon.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("attempts")
public class Attempts {
  @Id
  private int id;
  private String username;
  private int attempts;

  public Attempts(String username, int attempts) {
    this.username = username;
    this.attempts = attempts;
  }
}
