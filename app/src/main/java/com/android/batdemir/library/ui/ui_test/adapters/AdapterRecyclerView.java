package com.android.batdemir.library.ui.ui_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.batdemir.library.databinding.ItemPlayerBinding;
import com.android.batdemir.library.models.Player;
import com.android.batdemir.library.R;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter {

    private Context context;
    private List<Player> models;
    private ItemPlayerBinding binding;

    public AdapterRecyclerView(Context context, List<Player> models) {
        this.context = context;
        this.models = models;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Player model;

        MyViewHolder(ItemPlayerBinding binding) {
            super(binding.getRoot());
        }

        void setData(Player model) {
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
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_player, viewGroup, false);
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
