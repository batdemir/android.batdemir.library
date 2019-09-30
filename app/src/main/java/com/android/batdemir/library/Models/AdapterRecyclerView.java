package com.android.batdemir.library.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.RecyclerViewItemBinding;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Player> models;
    private RecyclerViewItemBinding binding;

    public AdapterRecyclerView(Context context, ArrayList<Player> models) {
        this.context = context;
        this.models = models;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Player model;

        public MyViewHolder(RecyclerViewItemBinding binding) {
            super(binding.getRoot());
        }

        public void setData(Player model) {
            this.model = model;
            binding.txtEditAge.setText(String.valueOf(model.getAge()));
            binding.txtEditating.setText(String.valueOf(model.getRating()));
            binding.txtEditClub.setText(model.getClub());
            binding.txtEditName.setText(model.getName());
            binding.txtEditNationality.setText(model.getNationality());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_view_item, viewGroup, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.setData(models.get(i));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public ArrayList<Player> getModels() {
        return models;
    }
}
