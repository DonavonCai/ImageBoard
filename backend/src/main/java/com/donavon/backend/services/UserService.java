package com.donavon.backend.services;

import java.util.List;

import com.donavon.backend.exception.DupEmailException;
import com.donavon.backend.exception.DupUsernameException;
import com.donavon.backend.exception.UndefinedFieldException;
import com.donavon.backend.model.LoginInfo;
import com.donavon.backend.model.User;
import com.donavon.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService { // TODO: should i extract into another service?
  @Autowired
  private UserRepository repo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<User> user = repo.findByUsername(username);
    if (user.isEmpty()) { // TODO: this is weird. Consider using Optional<User> in repo?
      throw new UsernameNotFoundException("User not present.");
    }
    return user.get(0);
  }

  public ResponseEntity<?> login(LoginInfo info) {
    // TODO: implement user sessions?
    List<User> users = repo.findByUsername(info.getUsername());

    if (users.isEmpty()) {
      // TODO: return status not found?
    }
    User user = users.get(0);
    if (user.getPassword().equals(info.getPassword())) {
      // TODO: return ok
    }
    // TODO: return not created?
    return ResponseEntity.ok().body("Logged in");
  }

  public ResponseEntity<?> createUser(User user) {
    // TODO: instead, try loadUserByUsername?
    if (repo.existsByUsername(user.getUsername())) {
      throw new DupUsernameException();
    }
    if (repo.existsByEmail(user.getEmail())) {
      throw new DupEmailException();
    }

    String hashedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(hashedPassword);

    try {
      repo.insert(user);
    } catch(Exception err) {
      System.out.println(err);
      return ResponseEntity.internalServerError()
             .body("Error: internal server error!");
    }
    return ResponseEntity.ok().body("User registered successfully!");
  }

  public ResponseEntity<?> existsBy(String field, String data) {
    Boolean result = false;
    if (field.equals("username")) {
      result = repo.existsByUsername(data);
    }
    else if (field.equals("email")) {
      result = repo.existsByEmail(data);
    }
    else {
      throw new UndefinedFieldException(field);
    }
    return ResponseEntity.ok().body(result);
  }
}
