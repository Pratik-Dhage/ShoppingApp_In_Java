package com.example.shoppingapp_in_java.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp_in_java.R;
import com.example.shoppingapp_in_java.databinding.SingleItemNewsBinding;
import com.example.shoppingapp_in_java.databinding.SingleItemProductBinding;
import com.example.shoppingapp_in_java.home.model.Articles;
import com.example.shoppingapp_in_java.home.model.Products;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolderClass>{

    Context context;
    ArrayList<Articles> articlesArrayList;
    SingleItemNewsBinding binding;

    // call this constructor in MainActivity
    public NewsAdapter(Context con, ArrayList arrayList) {
        this.context = con;
        this.articlesArrayList = arrayList;
    }

    // call this constructor in HomeViewModel
    public NewsAdapter(ArrayList<Articles> arrListData) {
        this.articlesArrayList = arrListData;
    }


    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_news, null, false);
        return new MyViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass holder, int position) {

        Articles a = articlesArrayList.get(position);

       holder.title.setText(a.getTitle());
        holder.author.setText(a.getAuthor());
        holder.publishedAt.setText(a.getPublishedAt());

        Glide.with(context).load(a.getUrlToImage()).into(holder.ivNews);

    }

    @Override
    public int getItemCount() {
         if(articlesArrayList.isEmpty()) return  0;
               else return articlesArrayList.size();
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

        TextView title, author, publishedAt;
        ImageView ivNews;

        public MyViewHolderClass(@NonNull View itemView) {
            super(itemView);

            title = binding.txtNewsTitle;
            author = binding.txtAuthor;
            publishedAt = binding.txtPublishedAt;
            ivNews = binding.ivNews;
        }

    }
}
