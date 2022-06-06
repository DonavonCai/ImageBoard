package com.donavon.backend.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.donavon.backend.model.User;
import com.donavon.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
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
  public String login(HttpServletRequest request, HttpSession session) {
     session.setAttribute(
        "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
     );
     return "Spring boot login request";
  }

  private String getErrorMessage(HttpServletRequest request, String key) {
    Exception exception = (Exception) request.getSession().getAttribute(key);
    String error = "";
    if (exception instanceof BadCredentialsException) {
       error = "Invalid username and password!";
    } else if (exception instanceof LockedException) {
       error = exception.getMessage();
    } else {
       error = "Invalid username and password!";
    }
    return error;
 }

  @PostMapping("/login_success_handler")
  public ResponseEntity<?> loginSuccessHandler() {
    // return "logged in";
    return ResponseEntity.ok().body("logged in");
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
