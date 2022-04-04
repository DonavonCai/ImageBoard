package com.donavon.backend.services;

import org.springframework.stereotype.Service;
import org.imgscalr.Scalr;
import java.awt.image.BufferedImage;

@Service
public class ImageResizeService {
  BufferedImage resize(BufferedImage originalImage, int targetWidth) {
    return Scalr.resize(originalImage, targetWidth);
  }
}
