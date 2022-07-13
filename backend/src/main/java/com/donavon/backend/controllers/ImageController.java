package com.donavon.backend.controllers;

// import com.donavon.backend.services.ImageUploadService;

import com.donavon.backend.services.ImageDownloadService;
import com.donavon.backend.services.StorageService;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;

@RestController
public class ImageController {
  @Autowired
  private ImageDownloadService imageDownloadService;
  // @Autowired
  // private ImageUploadService imageUploadService;

  @GetMapping("/img")
  public ResponseEntity<InputStreamResource> getImage() throws IOException {
    return this.imageDownloadService.getImage();
  }
}
