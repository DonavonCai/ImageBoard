package com.donavon.backend.controllers;

import com.donavon.backend.model.User;
import com.donavon.backend.model.LoginInfo;
import com.donavon.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  UserService userService;

  // todo: capture info from query params
  @GetMapping("/login")
  public String login() {
    return "Spring boot got a request for login.";
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user) {
    return userService.createUser(user);
  }

  @GetMapping("/register")
  public ResponseEntity<?> existsBy(@RequestParam String field, @RequestParam String data) {
    return userService.existsBy(field, data);
  }
}
