package com.donavon.backend.services;

import com.donavon.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
  @Autowired
  private UserRepository repository;
}
