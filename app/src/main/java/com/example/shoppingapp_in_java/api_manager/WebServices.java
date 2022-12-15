package com.example.shoppingapp_in_java.api_manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//WebServices will store Domain(base url) and parameters required
public class WebServices {
/*

    //for News Api
    public String Domain = "https://newsapi.org/v2/"; // Base Url
    public String api_key = "&apiKey=0964afd15f5d4897b36e8541e1f9ab7e";
    public String top_headlines = "top-headlines?";
    public String country_india = "country=in";

    //for Fake Store Api
    public static String Domain2 = "https://fakestoreapi.com"; //Base Url
    //  public String allProducts = "/products";

*/

    private static Retrofit retrofit = null;

    public static RestClient create() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(); // Live
        // logging.level = HttpLoggingInterceptor.Level.BODY; // Live
        logging.level(HttpLoggingInterceptor.Level.BODY);

        okHttpBuilder.connectTimeout(2, java.util.concurrent.TimeUnit.MINUTES);
        okHttpBuilder.readTimeout(2, TimeUnit.MINUTES).build();
        okHttpBuilder.writeTimeout(2, TimeUnit.MINUTES);
        okHttpBuilder.addInterceptor(logging);// Live
        //  val okHttpClient = okHttpBuilder.build();

        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofit == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofit.addConverterFactory(GsonConverterFactory.create(gson));
            retrofit.baseUrl(RestClient.Domain2).client(okHttpBuilder.build()).build();

        }

        return retrofit.create(RestClient.class);
    }

}
