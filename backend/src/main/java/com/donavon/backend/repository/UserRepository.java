package com.donavon.backend.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.donavon.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  List<User> findByUsername(String username);
  List<User> findByEmail(String email);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}
