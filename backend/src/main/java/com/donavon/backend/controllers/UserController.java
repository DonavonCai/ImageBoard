package com.donavon.backend.controllers;

import com.donavon.backend.model.User;
import com.donavon.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  UserRepository repository;

  // todo: capture info from query params
  @GetMapping("/login")
  public String login() {
    return "Spring boot got a request for login.";
  }

  // todo: capture info from query params
  @PostMapping("/register")
  public String register() {
    String username = "notARealPerson";
    String email = "fakeEmail@gmail.com";
    String password = "aGreatPassword";
    User user = new User(username, email, password);
    repository.insert(user);
    return "Spring inserted a new user";
  }
}
