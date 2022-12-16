package com.example.shoppingapp_in_java.home.view_model;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingapp_in_java.api_manager.WebServices;
import com.example.shoppingapp_in_java.helper_classes.Global;
import com.example.shoppingapp_in_java.home.HomeResponse;
import com.example.shoppingapp_in_java.home.adapter.HomeAdapter;
import com.example.shoppingapp_in_java.home.model.Products;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

       private Disposable subscribtion;
    private final MutableLiveData<HomeResponse> mutHomeResponseApi = new MutableLiveData<>();
    private final MutableLiveData<String> mutErrorResponse = new MutableLiveData<>();

    public MutableLiveData<HomeResponse> getMutHomeResponseApi() {
        return mutHomeResponseApi;
    }

    public MutableLiveData<String> getMutErrorResponse() {
        return mutErrorResponse;
    }


      //ArrayList<Products> arrListData ;
      ArrayList<Products> arrListData = new  ArrayList<Products>() ;
      HomeAdapter homeAdapter = new HomeAdapter(arrListData);


    public void updateNewsData(){
        homeAdapter.setData(arrListData);
    }

//Home Products Api

    public void getHomeProducts(Activity activity){


        subscribtion = (Disposable) Global.apiService().getAllProducts(WebServices.Domain2+WebServices.allProducts)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .unsubscribeOn(Schedulers.io())
                  .subscribe(
                          this::onHomeApiSuccess , this::onApiError
                  );

    }


    private void onHomeApiSuccess(HomeResponse result) {
        mutHomeResponseApi.setValue(result);
    }

    private void onApiError(Throwable error) {
        mutErrorResponse.setValue(error.getLocalizedMessage());
    }


}


