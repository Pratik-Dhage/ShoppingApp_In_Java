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
import com.example.shoppingapp_in_java.home.adapter.HomeAdapter;
import com.example.shoppingapp_in_java.home.adapter.NewsAdapter;
import com.example.shoppingapp_in_java.home.adapter.RawAdapter;
import com.example.shoppingapp_in_java.home.model.Articles;
import com.example.shoppingapp_in_java.home.model.Products;
import com.example.shoppingapp_in_java.home.model.RawProducts;
import com.example.shoppingapp_in_java.home.view_model.HomeViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    View view;
    HomeViewModel viewModel;
    List<Object> rawProductsItem = new ArrayList<>(); // for Fetching data from Json file stored locally in raw folder

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFields();

        if(NetworkUtilities.getConnectivityStatus(this)){

          //  initObserver(); //first observe
          //  callApi(); //then call api
            callFakeStoreApiFromRaw();  // for JSON Response Stored Locally
        }
        else Global.showSnackBar(view,getResources().getString(R.string.connection_error));


        onClickListeners();


    }

    // to call Locally Stored JSON File
    private void callFakeStoreApiFromRaw() {

        setUpRawRecyclerView();
        try {
            String jsonString = readJsonFromRaw();
            JSONArray jsonArray = new JSONArray(jsonString);

            for(int i = 0 ; i<=jsonArray.length() ; ++i){

                JSONObject itemJsonObject = jsonArray.getJSONObject(i);
                String title = itemJsonObject.getString("title");
                String category = itemJsonObject.getString("category");
                String price = itemJsonObject.getString("price");
                String image = itemJsonObject.getString("image");

                RawProducts rawProducts = new RawProducts(title,category,price,image);
                 rawProductsItem.add(rawProducts);

            }
          //  setUpRawRecyclerView();

        }
        catch(Exception e){
            Global.showSnackBar(view,e.toString());
            System.out.println("Here :"+ e);
        }

    }

    private String readJsonFromRaw() throws IOException {
        InputStream inputStream = null;
        String jsonString = null ;
        StringBuilder stringBuilder = new StringBuilder();

        try{
            inputStream = getResources().openRawResource(R.raw.fake_store_products_api);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

       while((jsonString = bufferedReader.readLine()) !=null){
           stringBuilder.append(jsonString);
       }

        }

        finally {
                  if(inputStream!=null){ inputStream.close();  }
        }

     return new String(stringBuilder);
    }

    private void callApi() {
        viewModel.getHomeNews(this); //for news
        viewModel.getHomeProducts(this); // for products
    }

    private void setUpNewsRecyclerView() {

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

    private void setUpProductsRecyclerView(){
          viewModel.updateProductsData();
        RecyclerView recyclerView = binding.rvMain;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new HomeAdapter(viewModel.arrListProductsData));
    }

    private void setUpRawRecyclerView(){
        //setupRawRecyclerView
        RecyclerView recyclerView = binding.rvMain;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RawAdapter(rawProductsItem));

    }

    private void initObserver() {

        //fake store api
      /*  viewModel.getMutHomeResponseApi().observe(this, result -> {

            if(NetworkUtilities.getConnectivityStatus(this)){

                if(result!=null)
                {
                    viewModel.arrListProductsData.clear();
                    viewModel.arrListProductsData.addAll(result.getProducts());
                    setUpProductsRecyclerView();
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
                    setUpNewsRecyclerView();

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