package com.example.shoppingapp_in_java.home;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;

public class HomeResponse implements Serializable {
  private String image;

  private float price;

  private Rating rating;

  private String description;

  private Integer id;

  private String title;

  private String category;

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Float getPrice() {
    return this.price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Rating getRating() {
    return this.rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public static class Rating implements Serializable {
    private BigDecimal rate;

    private Integer count;

    public BigDecimal getRate() {
      return this.rate;
    }

    public void setRate(BigDecimal rate) {
      this.rate = rate;
    }

    public Integer getCount() {
      return this.count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }
  }
}
