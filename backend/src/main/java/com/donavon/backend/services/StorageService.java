package com.donavon.backend.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Service
public class StorageService {
  @Autowired
  private AmazonS3 amazonS3;

  @Value("${s3.bucket.name}")
  private String s3BucketName;

  @Async
  public ResponseEntity<InputStreamResource> findByName(String fileName) {
    fileName = "n02088364_129.jpg";
    S3Object img = amazonS3.getObject(s3BucketName, fileName);

    InputStream is = img.getObjectContent();
    InputStreamResource resource = new InputStreamResource(is);

    return ResponseEntity
    .ok().contentType(MediaType.IMAGE_JPEG)
    .body(resource);
  }
}
