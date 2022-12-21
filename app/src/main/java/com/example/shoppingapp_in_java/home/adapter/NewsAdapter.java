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
import com.example.shoppingapp_in_java.databinding.SingleItemNewsBinding;
import com.example.shoppingapp_in_java.helper_classes.Global;
import com.example.shoppingapp_in_java.home.model.Articles;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolderClass>{


    ArrayList<Articles> articlesArrayList;

/*

    // call this constructor in MainActivity
    public NewsAdapter(Context con, ArrayList arrayList) {
        this.context = con;
        this.articlesArrayList = arrayList;
    }
*/

    // call this constructor in HomeViewModel & MainActivity
    public NewsAdapter(ArrayList<Articles> arrListData) {
        this.articlesArrayList = arrListData;
    }


    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleItemNewsBinding view = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.single_item_news,parent,false);

        return new MyViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass holder, int position) {

        Articles a = articlesArrayList.get(position);
        Context context = holder.itemView.getContext();

       holder.binding.txtNewsTitle.setText(a.getTitle());
        holder.binding.txtAuthor.setText(a.getAuthor());
        holder.binding.txtPublishedAt.setText(a.getPublishedAt());

        Glide.with(context).load(a.getUrlToImage()).into(holder.binding.ivNews);

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public ArrayList setData( ArrayList<Articles> data)  {
        if (data.isEmpty()) {
            articlesArrayList =  new ArrayList();
        }
        articlesArrayList = data;
        notifyDataSetChanged();

        return articlesArrayList;
    }


    class MyViewHolderClass extends RecyclerView.ViewHolder {


        private SingleItemNewsBinding binding;

        public MyViewHolderClass(SingleItemNewsBinding binding) {
            super(binding.getRoot());

               this.binding = binding;

        }

    }
}
