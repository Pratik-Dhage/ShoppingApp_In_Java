package com.example.shoppingapp_in_java.home.model;

//Model class for Raw JSON file fake_store_products_api
public class RawProducts {

    private String title;
    private String category;
    private String price;
    private String image;

    public RawProducts(String title, String category, String price , String image) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
