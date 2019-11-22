package com.android.batdemir.library.ui.ui_test.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.library.ui.ui_test.adapters.AdapterRecyclerView;
import com.android.batdemir.library.api.CallTest;
import com.android.batdemir.library.models.Player;
import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityRecyclerBinding;
import com.android.batdemir.mylibrary.api.Connect;
import com.android.batdemir.mylibrary.api.ConnectServiceErrorListener;
import com.android.batdemir.mylibrary.api.ConnectServiceListener;
import com.android.batdemir.mylibrary.api.RetrofitClient;
import com.android.batdemir.mylibrary.components.dialog.MyAlertDialog;
import com.android.batdemir.mylibrary.tools.swipe.SwipeController;
import com.android.batdemir.mylibrary.tools.swipe.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Response;

public class RecyclerActivity extends BaseActivity implements
        ConnectServiceListener,
        ConnectServiceErrorListener {

    private Context context;
    private ActivityRecyclerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false, true, "RecyclerView", 16f, R.style.AppThemeActionBar);
    }

    @Override
    public void getObjectReferences() {
        context = this;
        if (binding == null)
            binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_recycler);
    }

    @Override
    public void loadData() {
        setupRecyclerView();
    }

    @Override
    public void setListeners() {
        binding.btnPreviousPage.setOnClickListener(v -> finish());

        binding.btnConnectService.setOnClickListener(v -> {
            RetrofitClient.setBaseUrl("https://api.github.com");
            new Connect().connect(context, RetrofitClient.getInstance().create(CallTest.class).callTest(), "Test");
        });
    }

    @Override
    public void onSuccess(String operationType, Response response) {
        MyAlertDialog.getInstance("onSuccess\n" + Objects.requireNonNull(response.body()).toString(), MyAlertDialog.DialogStyle.WARNING).show(getSupportFragmentManager(), "success");
    }

    @Override
    public void onFailure(String operationType, Response response) {
        MyAlertDialog.getInstance("onFailure\n", MyAlertDialog.DialogStyle.ACTION).show(getSupportFragmentManager(), "failure");
    }

    @Override
    public void onException(String operationType, String errorMessage) {
        Log.v("TEST", "TEST");
    }

    private void setupRecyclerView() {
        List<Player> players = new ArrayList<>();
        String name = "Batuhan";
        String nationality = "Turkey";
        String club = "BatSport";

        for (int i = 0; i < 20; i++) {
            players.add(i, new Player(name + players.size(), nationality, club, (9 + i), (40 - i)));
        }

        AdapterRecyclerView adapterRecyclerView = new AdapterRecyclerView(context, players);
        binding.recyclerListView.setAdapter(adapterRecyclerView);
        binding.recyclerListView.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
        binding.recyclerListView.setItemViewCacheSize(players.size());
        binding.recyclerListView.setHasFixedSize(true);

        SwipeController swipeControllerDelete = new SwipeController(true, getDrawable(android.R.drawable.ic_menu_delete), new SwipeControllerActions() {
            @Override
            public void onLeftSwiped(int position, View rootView, String message, View.OnClickListener onClickListener) {
                Player player = adapterRecyclerView.getModels().get(position);
                adapterRecyclerView.getModels().remove(player);
                binding.recyclerListView.setAdapter(new AdapterRecyclerView(context, adapterRecyclerView.getModels()));
                binding.recyclerListView.smoothScrollToPosition(position);
                String str = "Deleted \n";
                str += "\tItem Name: " + player.getName();
                str += "\tItem Age: " + player.getAge().toString();
                str += "\tItem Rating: " + player.getRating().toString();
                super.onLeftSwiped(position, binding.rootRecyclerListView, str, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapterRecyclerView.getModels().add(position, player);
                        binding.recyclerListView.setAdapter(new AdapterRecyclerView(context, adapterRecyclerView.getModels()));
                        binding.recyclerListView.smoothScrollToPosition(position);
                    }
                });
            }
        });
        SwipeController swipeControllerEdit = new SwipeController(false, getDrawable(android.R.drawable.ic_menu_edit), new SwipeControllerActions() {
            @Override
            public void onRightSwiped(int position, View rootView, String message, View.OnClickListener onClickListener) {
                Player player = new Player("newBatdemir", "Turkey", "BatSpor", 100, 28);
                adapterRecyclerView.getModels().add(adapterRecyclerView.getItemCount(), player);
                binding.recyclerListView.setAdapter(new AdapterRecyclerView(context, adapterRecyclerView.getModels()));
                binding.recyclerListView.smoothScrollToPosition(adapterRecyclerView.getItemCount());
                String str = "Inserted \n";
                str += "\tItem Name: " + player.getName();
                str += "\tItem Age: " + player.getAge().toString();
                str += "\tItem Rating: " + player.getRating().toString();
                super.onRightSwiped(position, binding.rootRecyclerListView, str, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapterRecyclerView.getModels().remove(player);
                        binding.recyclerListView.setAdapter(new AdapterRecyclerView(context, adapterRecyclerView.getModels()));
                        binding.recyclerListView.smoothScrollToPosition(position);
                    }
                });
            }
        });

        ItemTouchHelper itemTouchHelperDelete = new ItemTouchHelper(swipeControllerDelete);
        itemTouchHelperDelete.attachToRecyclerView(binding.recyclerListView);

        ItemTouchHelper itemTouchHelperEdit = new ItemTouchHelper(swipeControllerEdit);
        itemTouchHelperEdit.attachToRecyclerView(binding.recyclerListView);
    }
}
