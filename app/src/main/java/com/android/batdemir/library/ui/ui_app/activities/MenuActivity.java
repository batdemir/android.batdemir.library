package com.android.batdemir.library.ui.ui_app.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMenuBinding;
import com.android.batdemir.library.models.Event;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.library.ui.ui_app.adapters.AdapterEvents;
import com.android.batdemir.mylibrary.tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity implements
        AdapterEvents.EventClickListener {

    private Context context;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(true, false, "Menu", 16f, R.style.AppThemeActionBar);
    }

    @Override
    public void getObjectReferences() {
        context = this;
        if (binding == null) {
            binding = ActivityMenuBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
        }
    }

    @Override
    public void loadData() {
        setupRecyclerView();
    }

    @Override
    public void setListeners() {
        //Nothing
    }

    private void setupRecyclerView() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Define\nGroup", R.drawable.ic_group, null));
        events.add(new Event("Define\nTask", R.drawable.ic_task, null));
        events.add(new Event("To-do\nList", R.drawable.ic_clipboard, null));
        events.add(new Event("Settings", R.drawable.ic_settings, null));

        binding.recyclerEvents.setAdapter(new AdapterEvents(context, events));
        binding.recyclerEvents.setItemViewCacheSize(events.size());
        binding.recyclerEvents.setHasFixedSize(true);
    }

    @Override
    public void eventClick(Event event) {
        if (event.getTo() == null)
            Toast.makeText(context, "Not Implemented", Toast.LENGTH_SHORT).show();
        Tool.getInstance(context).move(event.getTo(), true, true, null);
    }
}
