package com.example.shoppingapp_in_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.shoppingapp_in_java.api_manager.WebServices;
import com.example.shoppingapp_in_java.databinding.ActivityMainBinding;
import com.example.shoppingapp_in_java.helper_classes.Global;
import com.example.shoppingapp_in_java.helper_classes.NetworkUtilities;
import com.example.shoppingapp_in_java.helper_classes.SharedPreferenceHelper;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       initializeFields();
       onClickListeners();
       initObserver();

    }

    private void initObserver() {
    }

    private void onClickListeners() {

        binding.bottomNavMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){


                }

                return false;
            }
        });
    }

    private void initializeFields() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        view = binding.getRoot();

    }
}