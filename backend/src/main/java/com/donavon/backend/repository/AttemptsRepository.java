package com.donavon.backend.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.donavon.backend.model.Attempts;

public interface AttemptsRepository extends MongoRepository<Attempts, Integer>{
  Optional<Attempts> findByUsername(String username);
}
