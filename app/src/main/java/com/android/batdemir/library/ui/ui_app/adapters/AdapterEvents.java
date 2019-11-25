package com.android.batdemir.library.ui.ui_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.batdemir.library.models.Event;
import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ItemEventBinding;

import java.util.List;

public class AdapterEvents extends RecyclerView.Adapter {

    private Context context;
    private List<Event> models;
    private ItemEventBinding binding;

    public AdapterEvents(Context context, List<Event> models) {
        this.context = context;
        this.models = models;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Event model;

        private MyViewHolder(ItemEventBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
        }

        private void setData(Event model) {
            this.model = model;

            binding.imgEditEvent.setImageResource(model.getEventIcon());
            binding.txtEditEvent.setText(model.getEventName());
        }

        @Override
        public void onClick(View v) {
            EventClickListener eventClickListener = (EventClickListener) context;
            eventClickListener.eventClick(model);
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

    public interface EventClickListener {
        void eventClick(Event event);
    }

}
