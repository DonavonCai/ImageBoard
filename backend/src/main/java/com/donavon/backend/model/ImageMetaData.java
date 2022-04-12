package com.donavon.backend.model;

import java.net.URL;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document("images")
public class ImageMetaData {
  @Id
  private String id;
  private URL imageURL;
  private URL thumbnailURL;
  private String author;
  private Date date;
}
