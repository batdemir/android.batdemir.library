package com.android.batdemir.library.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.batdemir.library.Models.Event;
import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ItemEventBinding;
import com.android.batdemir.mylibrary.Tools.Tool;

import java.util.List;

public class AdapterEvents extends RecyclerView.Adapter {

    private Context context;
    private List<Event> models;
    private ItemEventBinding binding;

    public AdapterEvents(Context context, List<Event> models) {
        this.context = context;
        this.models = models;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Event model;

        MyViewHolder(ItemEventBinding binding) {
            super(binding.getRoot());
        }

        private void setData(Event model) {
            this.model = model;

            binding.imgEditEvent.setImageResource(model.getEventIcon());
            binding.txtEditEvent.setText(model.getEventName());
        }

        @Override
        public void onClick(View v) {
            new Tool(context).move(model.getTo(), true, true, null);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_event, viewGroup, false);
        return new AdapterEvents.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        AdapterEvents.MyViewHolder holder = (AdapterEvents.MyViewHolder) viewHolder;
        holder.setData(models.get(i));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public List<Event> getModels() {
        return models;
    }

}
