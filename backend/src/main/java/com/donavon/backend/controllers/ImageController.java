package com.donavon.backend.controllers;

import com.donavon.backend.services.ImageService;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;

@RestController
public class ImageController {
  @Autowired
  private ImageService imageService;

  @GetMapping("/img")
  public ResponseEntity<InputStreamResource> getImage() throws IOException {
    var fileName = "image/mikhail-volkov-4OBO3zOBKEk-unsplash.jpg";
    return this.imageService.getImage(fileName);
  }
}
