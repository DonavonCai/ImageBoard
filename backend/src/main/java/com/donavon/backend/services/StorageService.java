package com.donavon.backend.services;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.lang.Thread;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class StorageService {
  @Autowired
  private AmazonS3 amazonS3;

  @Value("${s3.bucket.name}")
  private String s3BucketName;

  // @Async
  public BufferedImage findImageByName(String fileName) throws IOException {
    // Thread.sleep(10000);
    S3Object imgObject = amazonS3.getObject(s3BucketName, fileName);

    InputStream stream = imgObject.getObjectContent();
    BufferedImage img = ImageIO.read(stream);
    return img;
  }
}
