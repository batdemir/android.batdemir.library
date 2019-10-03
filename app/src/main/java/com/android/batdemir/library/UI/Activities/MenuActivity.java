package com.android.batdemir.library.UI.Activities;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.batdemir.library.App.Base.BaseActivity;
import com.android.batdemir.library.UI.Adapters.AdapterEvents;
import com.android.batdemir.library.Models.Event;
import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMenuBinding;
import com.android.batdemir.mylibrary.Tools.Tool;

import java.util.ArrayList;

public class MenuActivity extends BaseActivity implements
        AdapterEvents.EventClickListener {

    private Context context;
    private ActivityMenuBinding binding;

    @Override
    public void getObjectReferences() {
        init(true, false, "Menu", 16f);
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_menu);
    }

    @Override
    public void loadData() {
        setupRecyclerView();
    }

    @Override
    public void setListeners() {
        //
    }

    private void setupRecyclerView() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event("Define\nGroup", R.drawable.ic_group, null));
        events.add(new Event("Define\nTask", R.drawable.ic_task, null));
        events.add(new Event("To-do\nList", R.drawable.ic_clipboard, null));
        events.add(new Event("Settings", R.drawable.ic_settings, SettingsActivity.class));

        binding.recyclerEvents.setAdapter(new AdapterEvents(context, events));
        binding.recyclerEvents.setItemViewCacheSize(events.size());
        binding.recyclerEvents.setHasFixedSize(true);
    }

    @Override
    public void eventClick(Event event) {
        if (event.getTo() == null)
            Toast.makeText(context, "Not Implemented", Toast.LENGTH_SHORT).show();
        new Tool(context).move(event.getTo(), true, true, null);
    }
}
