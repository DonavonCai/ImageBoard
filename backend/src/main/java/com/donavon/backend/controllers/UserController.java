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

  @GetMapping("/login")
  public String login() {
    return "Spring boot got a request for login.";
  }

  @PostMapping("/login_success_handler")
  public String loginSuccessHandler() {
    return "logged in!!!";
  }

  @PostMapping("/login_failure_handler")
  public String loginFailureHandler() {
    return "failed :(";
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
