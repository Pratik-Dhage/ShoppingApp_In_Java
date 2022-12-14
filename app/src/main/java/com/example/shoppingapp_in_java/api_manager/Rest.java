package com.example.shoppingapp_in_java.api_manager;

import com.example.shoppingapp_in_java.model.Products;

import retrofit2.Call;
import retrofit2.http.GET;

//Rest Interface will have GET(),POST(),PUT() etc. api methods
public interface Rest {

    //Fake Store Api : https://fakestoreapi.com/products
    //Base Url :  https://fakestoreapi.com
    //endpoint : /products

    @GET("/products")
    Call<Products> getAllProducts();
}
