package com.donavon.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.donavon.backend.model.ImageMetaData;

public interface ImageRepository extends MongoRepository<ImageMetaData, String>{

}
