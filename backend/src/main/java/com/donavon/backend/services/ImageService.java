package com.donavon.backend.services;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
  public ResponseEntity<InputStreamResource> getImage(String fileName) throws IOException {
    var imgFile = new ClassPathResource(fileName);
    return ResponseEntity
    .ok().contentType(MediaType.IMAGE_JPEG)
    .body(new InputStreamResource(imgFile.getInputStream()));
  }
}