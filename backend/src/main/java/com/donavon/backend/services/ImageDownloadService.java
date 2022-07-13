package com.donavon.backend.services;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;

@Service
public class ImageDownloadService {
  @Autowired
  private ImageResizeService imageResizeService;

  @Autowired
  private StorageService storageService;

  // Interface:
  // TODO: find a way to make this asynchronous?
  public ResponseEntity<InputStreamResource> getImage() throws IOException, FileNotFoundException {
    // Read file into a buffered image
    String fileName = "n02088364_129.jpg";
    BufferedImage img = storageService.findImageByName(fileName);

    // TODO: find a way to resize images cleanly
    img = imageResizeService.resize(img, 300);

    // Create an input stream for the ResponseEntity
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(img, "jpeg", os);
    ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

    // Return image
    return ResponseEntity
           .ok().contentType(MediaType.IMAGE_JPEG)
           .body(new InputStreamResource(is));
  }

  // Helpers:
  // private File getFile(String fileName) throws FileNotFoundException{
  //   return ResourceUtils.getFile("classpath:image/" + fileName);
  // }
}