package com.donavon.backend.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
import java.awt.image.BufferedImage;
import org.springframework.util.ResourceUtils;

@Service
public class ImageDownloadService {
  @Autowired
  private ImageResizeService imageResizeService;

  // Interface:
  public ResponseEntity<InputStreamResource> getImage(String fileName) throws IOException, FileNotFoundException {
    // Read file into a buffered image
    File file = getFile(fileName);
    BufferedImage img = ImageIO.read(file);
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
  private File getFile(String fileName) throws FileNotFoundException{
    return ResourceUtils.getFile("classpath:image/" + fileName);
  }
}