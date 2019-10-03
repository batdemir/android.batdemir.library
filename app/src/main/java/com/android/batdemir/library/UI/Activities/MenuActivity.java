package com.android.batdemir.library.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.UI.Adapters.AdapterEvents;
import com.android.batdemir.library.Models.Event;
import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMenuBinding;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private Context context;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_menu);
        getSupportActionBar();
        getSupportActionBar().setTitle("Menu");
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event("Define\nGroup", R.drawable.ic_group, null));
        events.add(new Event("Define\nTask", R.drawable.ic_task, null));
        events.add(new Event("To-do\nList", R.drawable.ic_clipboard, null));

        binding.recyclerEvents.setAdapter(new AdapterEvents(context, events));
        binding.recyclerEvents.setItemViewCacheSize(events.size());
        binding.recyclerEvents.setHasFixedSize(true);
    }
}
