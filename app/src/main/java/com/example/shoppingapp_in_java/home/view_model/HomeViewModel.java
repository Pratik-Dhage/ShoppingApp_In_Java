package com.example.shoppingapp_in_java.home.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingapp_in_java.home.HomeResponse;
import com.example.shoppingapp_in_java.home.adapter.HomeAdapter;
import com.example.shoppingapp_in_java.home.model.Products;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class HomeViewModel extends ViewModel {

       private Disposable subscribe;
       MutableLiveData<HomeResponse> mutHomeResponse = new MutableLiveData();
       MutableLiveData<String> mutErrorResponse = new MutableLiveData();

      ArrayList<Products> arrListData ;
      HomeAdapter homeAdapter = new HomeAdapter(arrListData);



}
