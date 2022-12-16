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
import com.example.shoppingapp_in_java.home.model.Products;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolderClass> {

    Context context;
    ArrayList<Products> ProductsModelClassArrayList;
    SingleItemProductBinding binding;

    // call this constructor in MainActivity
    public HomeAdapter(Context con, ArrayList arrayList) {
        this.context = con;
        this.ProductsModelClassArrayList = arrayList;
    }

    // call this constructor in HomeViewModel
    public HomeAdapter(ArrayList<Products> arrListData) {
        this.ProductsModelClassArrayList = arrListData;
    }


    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_item_product, null, false);
        return new MyViewHolderClass(view);

    }

    @SuppressLint("SetTextI18n") // for float price to String
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass holder, int position) {

        Products a = ProductsModelClassArrayList.get(position);

        holder.title.setText(a.getTitle());
        holder.category.setText(a.getCategory());
        holder.price.setText(a.getPrice().toString());

        Glide.with(context).load(a.getImage()).into(holder.ivProduct);
    }

    @Override
    public int getItemCount() {
        return ProductsModelClassArrayList.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public ArrayList setData( ArrayList<Products> data)  {
        if (data.isEmpty()) {
           ProductsModelClassArrayList =  new ArrayList();
        }
        ProductsModelClassArrayList = data;
        notifyDataSetChanged();

        return ProductsModelClassArrayList;
    }


    class MyViewHolderClass extends RecyclerView.ViewHolder {

        TextView title, category, price;
        ImageView ivProduct;

        public MyViewHolderClass(@NonNull View itemView) {
            super(itemView);

            title = binding.txtTitle;
            category = binding.txtCategory;
            price = binding.txtPrice;
            ivProduct = binding.ivProduct;
        }
    }
}
