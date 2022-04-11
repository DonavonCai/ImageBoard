package com.donavon.backend.model;

import java.net.URL;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("images")
public class ImageMetaData {
  @Id
  private String id;

  private URL imageURL;
  private URL thumbnailURL;
  private String author;
  private Date date;

  public ImageMetaData(String id, URL imageURL, URL thumbnailURL, String author, Date date) {
    super();
    this.id = id;
    this.imageURL = imageURL;
    this.thumbnailURL = thumbnailURL;
    this.author = author;
    this.date = date;
  }

  // Getters:
  public URL getImageURL() { return this.imageURL; }
  public URL getThumbnailURL() { return this.thumbnailURL; }
  public String getAuthor() { return this.author; }
  public Date getDate() { return this.date; }

  // Setters:
  public void setImageURL(URL imageURL) { this.imageURL = imageURL; }
  public void setThumbnailURL(URL thumbnailURL) { this.thumbnailURL = thumbnailURL; }
  public void setAuthor(String author) { this.author = author; }
  public void setDate(Date date) { this.date = date; }
}
