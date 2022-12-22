package com.example.shoppingapp_in_java.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp_in_java.R;
import com.example.shoppingapp_in_java.databinding.SingleItemProductBinding;
import com.example.shoppingapp_in_java.home.HomeResponse;
import com.example.shoppingapp_in_java.home.model.Products;
import com.example.shoppingapp_in_java.home.model.RawProducts;

import java.util.ArrayList;
import java.util.List;

public class RawAdapter extends RecyclerView.Adapter<RawAdapter.MyViewHolderClass> {

    // Context context;
    //ArrayList<Products> ProductsModelClassArrayList;
     List<Object> listRecyclerItem;


    // call this constructor in MainActivity
    public RawAdapter( List<Object> listRecyclerItem) {
        this.listRecyclerItem = listRecyclerItem;
    }


    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SingleItemProductBinding view = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.single_item_product, null, false) ;
        return new MyViewHolderClass(view);

    }

    @SuppressLint("SetTextI18n") // for float price to String
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass holder, int position) {

        RawProducts a = (RawProducts) listRecyclerItem.get(position);
        Context context = holder.itemView.getContext();

        holder.binding.txtTitle.setText(a.getTitle());
        holder.binding.txtCategory.setText(a.getCategory());
        holder.binding.txtPrice.setText(a.getPrice());

        Glide.with(context).load(a.getImage()).into(holder.binding.ivProduct);
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }




    class MyViewHolderClass extends RecyclerView.ViewHolder {

        private   SingleItemProductBinding binding;

        public MyViewHolderClass(SingleItemProductBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
