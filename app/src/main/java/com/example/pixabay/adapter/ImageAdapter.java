package com.example.pixabay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pixabay.databinding.ItemImageBinding;
import com.example.pixabay.network.model.Hit;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewholder> {

    public ImageAdapter(ArrayList<Hit> list) {
        this.list = list;
    }

    ArrayList<Hit> list;

    @NonNull
    @Override
    public ImageViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewholder(ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewholder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewholder extends RecyclerView.ViewHolder {
        ItemImageBinding binding;
        public ImageViewholder(ItemImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void onBind(Hit obj){
            Glide.with(binding.imageV).load(obj.getLargeImageURL()).into(binding.imageV);
        }
    }
}
