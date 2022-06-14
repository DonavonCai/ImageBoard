package com.donavon.backend.services;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

@Service
public class AmazonClient {
  private AmazonS3 s3client;

}
