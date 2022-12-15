package com.example.shoppingapp_in_java.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp_in_java.home.model.Products;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolderClass>{

    Context context;
    ArrayList<Products> ProductsModelClassArrayList;

    // call this constructor in MainActivity
    public HomeAdapter(Context con , ArrayList arrayList){
        this.context = con;
        this.ProductsModelClassArrayList= arrayList;
    }

    // call this constructor in HomeViewModel
    public HomeAdapter(ArrayList<Products> arrListData) {
        this.ProductsModelClassArrayList = arrListData;
    }


    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolderClass extends RecyclerView.ViewHolder {
       public MyViewHolderClass(@NonNull View itemView) {
           super(itemView);
       }
   }
}
