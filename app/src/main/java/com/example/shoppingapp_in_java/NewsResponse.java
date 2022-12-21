package com.example.shoppingapp_in_java;

import com.example.shoppingapp_in_java.home.model.Articles;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class NewsResponse implements Serializable {
  private Integer totalResults;

  private List<? extends Articles> articles;

  private String status;

  public Integer getTotalResults() {
    return this.totalResults;
  }

  public void setTotalResults(Integer totalResults) {
    this.totalResults = totalResults;
  }

  public List<? extends Articles> getArticles() {
    return this.articles;
  }

  public void setArticles(List<? extends Articles> articles) {
    this.articles = articles;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


}
