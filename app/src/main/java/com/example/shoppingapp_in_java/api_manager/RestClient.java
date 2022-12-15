package com.example.shoppingapp_in_java.api_manager;

import com.example.shoppingapp_in_java.home.model.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

//Rest Interface will have GET(),POST(),PUT() etc. api methods
public interface RestClient {

    //for News Api
    public String Domain = "https://newsapi.org/v2/"; // Base Url
    public String api_key = "&apiKey=0964afd15f5d4897b36e8541e1f9ab7e";
    public String top_headlines = "top-headlines?";
    public String country_india = "country=in";


    //Fake Store Api : https://fakestoreapi.com/products
    String Domain2 =  "https://fakestoreapi.com";
    //endpoint : /products

    @GET("/products")
    Call<Products> getAllProducts();




}
