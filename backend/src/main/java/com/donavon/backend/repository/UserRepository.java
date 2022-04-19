package com.donavon.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import com.donavon.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  List<User> findByUsername(String username);
  List<User> findByEmail(String email);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}
