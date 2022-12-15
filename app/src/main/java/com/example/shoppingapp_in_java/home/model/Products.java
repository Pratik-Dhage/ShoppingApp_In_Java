package com.example.shoppingapp_in_java.home.model;

// Model Class
public class Products {
	private String image;
	private Float price;
	private String description;
	private String title;
	private String category;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPrice(Float price){
		this.price = price;
	}

	public Float getPrice(){
		return price;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}
}
