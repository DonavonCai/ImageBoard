package com.donavon.backend.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.DecryptionFailureException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.secretsmanager.model.InternalServiceErrorException;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;
import com.amazonaws.services.secretsmanager.model.InvalidRequestException;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Configuration
public class AmazonS3Config {
  private String accessKeyId;
  private String accessKeySecret;
  @Value("${s3.region.name}")
  private String s3RegionName;

  @Bean
  public AmazonS3 getAmazonS3Client() {
    getSecret();
    // Get credentials
    final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
    final AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);

    // Create Amazon S3 client
    return AmazonS3ClientBuilder
      .standard()
      .withCredentials(provider)
      .withRegion(s3RegionName)
      .build();
  }

  private void getSecret() {
    String secretName = "ImageBoard/S3Keys";
    String region = "us-west-1";

    // Create a Secrets Manager client
    AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
                                  .withRegion(region)
                                  .build();

    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                            .withSecretId(secretName);
    GetSecretValueResult getSecretValueResult = null;

    try {
      getSecretValueResult = client.getSecretValue(getSecretValueRequest);
    } catch (DecryptionFailureException e) {
      System.out.println(e);
    } catch (InternalServiceErrorException e) {
      System.out.println(e);
    } catch (InvalidParameterException e) {
      System.out.println(e);
    } catch (InvalidRequestException e) {
      System.out.println(e);
    } catch (ResourceNotFoundException e) {
      System.out.println(e);
    } catch (SdkClientException e) {
      System.out.println(e);
    }

    // Something is wrong with the secret, check secrets manager on AWS console.
    if (getSecretValueResult.getSecretString() == null) {
      throw new SdkClientException("getSecretValueResult().getSecretString() is null.");
    }

    // Decrypts secret using the associated KMS key.
    String secret = getSecretValueResult.getSecretString();

    // Extract values from json string.
    JsonObject json = JsonParser.parseString(secret).getAsJsonObject();

    this.accessKeyId = json.get("AccessKeyID").getAsString();
    this.accessKeySecret = json.get("SecretAccessKey").getAsString();
  }
}
