package com.example.shoppingapp_in_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.shoppingapp_in_java.api_manager.WebServices;
import com.example.shoppingapp_in_java.databinding.ActivityMainBinding;
import com.example.shoppingapp_in_java.helper_classes.Global;
import com.example.shoppingapp_in_java.helper_classes.NetworkUtilities;
import com.example.shoppingapp_in_java.helper_classes.SharedPreferenceHelper;
import com.example.shoppingapp_in_java.home.HomeResponse;
import com.example.shoppingapp_in_java.home.adapter.HomeAdapter;
import com.example.shoppingapp_in_java.home.model.Products;
import com.example.shoppingapp_in_java.home.view_model.HomeViewModel;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    View view;
    HomeViewModel viewModel;
    ArrayList<Products> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeFields();
        setUpRecyclerView();
        if(NetworkUtilities.getConnectivityStatus(this)){
            initObserver();
        }
        else Global.showSnackBar(view,getResources().getString(R.string.connection_error));

        onClickListeners();


    }

    private void setUpRecyclerView() {

        RecyclerView.Adapter adapter = new HomeAdapter(list);
        RecyclerView recyclerView = binding.rvMain;
        viewModel.updateNewsData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initObserver() {

        viewModel.getMutHomeResponseApi().observe(this, result -> {

            if(NetworkUtilities.getConnectivityStatus(this)){

                if(result!=null)
                {
                    list.addAll(result.products);
                    Global.showToast(this,"Size of Products:"+result.products.size());
                }
            }

          else Global.showSnackBar(view,getResources().getString(R.string.connection_error));

        });


        //handle error response
        viewModel.getMutErrorResponse().observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Global.showSnackBar(view, error);
                System.out.println("Here: " + error);
            } else {
                Global.showSnackBar(view, getResources().getString(R.string.connection_error));
            }
        });


    }

    private void onClickListeners() {


    }

    private void initializeFields() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        list = new ArrayList<>();

    }
}