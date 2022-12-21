package com.example.shoppingapp_in_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.shoppingapp_in_java.databinding.ActivityMainBinding;
import com.example.shoppingapp_in_java.helper_classes.Global;
import com.example.shoppingapp_in_java.helper_classes.NetworkUtilities;
import com.example.shoppingapp_in_java.home.adapter.NewsAdapter;
import com.example.shoppingapp_in_java.home.model.Articles;
import com.example.shoppingapp_in_java.home.model.Products;
import com.example.shoppingapp_in_java.home.view_model.HomeViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    View view;
    HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFields();

        if(NetworkUtilities.getConnectivityStatus(this)){

            initObserver(); //first observe
            callApi(); //then call api
        }
        else Global.showSnackBar(view,getResources().getString(R.string.connection_error));


        onClickListeners();


    }

    private void callApi() {
        viewModel.getHomeNews(this); //for news
        viewModel.getHomeProducts(this); // for products
    }

    private void setUpRecyclerView() {

        //  viewModel.updateProductsData();
        viewModel.updateNewsHomeData();
        RecyclerView recyclerView = binding.rvMain;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
/*
        //fake store api
        recyclerView.setAdapter(new HomeAdapter(list));
        */
        //news api
        recyclerView.setAdapter(new NewsAdapter(viewModel.arrListNewsData));
       // recyclerView.setAdapter(viewModel.newsAdapter);

    }

    private void initObserver() {
/*

        //fake store api
        viewModel.getMutHomeResponseApi().observe(this, result -> {

            if(NetworkUtilities.getConnectivityStatus(this)){

                if(result!=null)
                {
                    list.addAll(result.getProducts());
                    Global.showToast(this,"Size of Products:"+result.getProducts().size());
                }
            }

          else Global.showSnackBar(view,getResources().getString(R.string.connection_error));

        });

        //handle products error response
        viewModel.getMutErrorResponse().observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Global.showSnackBar(view, error);
                System.out.println("Here: " + error);
            } else {
                Global.showSnackBar(view, getResources().getString(R.string.connection_error));
            }
        });

*/


        //news api
        viewModel.getMutNewsResponseApi().observe(this, result -> {

            if(NetworkUtilities.getConnectivityStatus(this)){

                if(result.getArticles()!=null)
                {
                    viewModel.arrListNewsData.clear();
                    viewModel.arrListNewsData.addAll(result.getArticles());

                    //set up recyclerview
                    setUpRecyclerView();

                    Global.showToast(this,result.getStatus()+" Size of Articles:"+result.getArticles().size());
                }
            } else Global.showSnackBar(view,getResources().getString(R.string.connection_error));


        });

        //handle news error response
        viewModel.getMutNewsErrorResponse().observe(this, error ->{

            if (error != null && !error.isEmpty()) {
                Global.showSnackBar(view, error);
                System.out.println("Here: " + error);
            } else {
                Global.showSnackBar(view, getResources().getString(R.string.connection_error));
            }
        });

    }

    private void onClickListeners() {

        binding.rvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Global.showToast(MainActivity.this,"Touched");
            }
        });

    }

    private void initializeFields() {
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setViewModel(viewModel);

    }
}