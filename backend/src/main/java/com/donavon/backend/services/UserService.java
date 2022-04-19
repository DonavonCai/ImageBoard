package com.donavon.backend.services;

import com.donavon.backend.exception.DupEmailException;
import com.donavon.backend.exception.DupUsernameException;
import com.donavon.backend.model.User;
import com.donavon.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository repo;

  public ResponseEntity<?> createUser(User user) {
    if (repo.existsByUsername(user.getUsername())) {
      throw new DupUsernameException();
    }
    if (repo.existsByEmail(user.getEmail())) {
      throw new DupEmailException();
    }

    try {
      repo.insert(user);
    } catch(Exception err) {
      System.out.println(err);
      return ResponseEntity.internalServerError()
             .body("Error: internal server error!");
    }
    return ResponseEntity.ok().body("User registered successfully!");
  }
}
