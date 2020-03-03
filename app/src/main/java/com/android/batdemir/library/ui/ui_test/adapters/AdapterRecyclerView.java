package com.android.batdemir.library.ui.ui_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.batdemir.library.databinding.ItemPlayerBinding;
import com.android.batdemir.library.models.Player;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter {

    private Context context;
    private List<Player> models;
    private ItemPlayerBinding binding;

    public AdapterRecyclerView(Context context, List<Player> models) {
        this.context = context;
        this.models = models;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private Player model;

        private MyViewHolder(ItemPlayerBinding binding) {
            super(binding.getRoot());
        }

        private void setData(Player model) {
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
        binding = ItemPlayerBinding.inflate(LayoutInflater.from(context), viewGroup, false);
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

    public List<Player> getModels() {
        return models;
    }
}
