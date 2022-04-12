package com.donavon.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.donavon.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
