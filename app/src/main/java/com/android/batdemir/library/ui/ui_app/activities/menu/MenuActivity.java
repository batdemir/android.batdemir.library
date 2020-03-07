package com.android.batdemir.library.ui.ui_app.activities.menu;

import android.widget.Toast;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMenuBinding;
import com.android.batdemir.library.models.Event;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_app.adapters.AdapterEvents;
import com.android.batdemir.mylibrary.tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity<ActivityMenuBinding, MenuController> implements
        AdapterEvents.EventClickListener {

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, "Menu", 16f, true);
    }

    @Override
    public void getObjectReferences() {
        //Nothing
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

        getBinding().recyclerEvents.setAdapter(new AdapterEvents(getBinding().getRoot().getContext(), events));
        getBinding().recyclerEvents.setItemViewCacheSize(events.size());
        getBinding().recyclerEvents.setHasFixedSize(true);
    }

    @Override
    public void eventClick(Event event) {
        if (event.getTo() == null)
            Toast.makeText(getBinding().getRoot().getContext(), "Not Implemented", Toast.LENGTH_SHORT).show();
        Tool.getInstance(getBinding().getRoot().getContext()).move(event.getTo(), true, true, null);
    }
}
