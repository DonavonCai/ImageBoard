package com.donavon.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {
  @Value("${access.key.id}")
  private String accessKeyId;
  @Value("${access.key.secret}")
  private String accessKeySecret;
  @Value("${s3.region.name}")
  private String s3RegionName;

  @Bean
  public AmazonS3 getAmazonS3Client() {
    // Get credentials
    final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
    final AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);

    // Get Amazon S3 client
    return AmazonS3ClientBuilder
      .standard()
      .withCredentials(provider)
      .withRegion(s3RegionName)
      .build();
  }
}
