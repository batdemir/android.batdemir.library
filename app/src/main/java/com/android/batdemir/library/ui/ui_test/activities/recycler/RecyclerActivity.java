package com.android.batdemir.library.ui.ui_test.activities.recycler;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.android.batdemir.library.R;
import com.android.batdemir.library.api.CallTest;
import com.android.batdemir.library.databinding.ActivityRecyclerBinding;
import com.android.batdemir.library.models.Player;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_test.activities.barcode.BarcodeActivity;
import com.android.batdemir.library.ui.ui_test.adapters.AdapterRecyclerView;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.components.helper.SwipeController;
import com.android.batdemir.mylibrary.components.listeners.SwipeControllerActions;
import com.android.batdemir.mylibrary.connection.Connect;
import com.android.batdemir.mylibrary.connection.ConnectServiceErrorListener;
import com.android.batdemir.mylibrary.connection.ConnectServiceListener;
import com.android.batdemir.mylibrary.connection.RetrofitClient;
import com.android.batdemir.mylibrary.tools.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Response;

public class RecyclerActivity extends BaseActivity<ActivityRecyclerBinding, RecyclerController> implements
        ConnectServiceListener,
        ConnectServiceErrorListener {

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, "RecyclerView", 16f, true);
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
        getBinding().btnNextPage.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(BarcodeActivity.class, true, true, null));

        getBinding().btnConnectService.setOnClickListener(v -> {
            RetrofitClient.setBaseUrl("https://api.github.com");
            new Connect().connect(getBinding().getRoot().getContext(), RetrofitClient.getInstance().create(CallTest.class).callTest(), "Test");
        });
    }

    @Override
    public void onSuccess(String operationType, Response response) {
        //MyAlertDialog.getInstance("onSuccess\n" + Objects.requireNonNull(response.body()).toString(), MyDialogStyle.WARNING).show(getSupportFragmentManager(), "success");
    }

    @Override
    public void onFailure(String operationType, Response response) {
        //MyAlertDialog.getInstance("onFailure\n", MyDialogStyle.ACTION).show(getSupportFragmentManager(), "failure");
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

        AdapterRecyclerView adapterRecyclerView = new AdapterRecyclerView(getBinding().getRoot().getContext(), players);
        getBinding().recyclerListView.setAdapter(adapterRecyclerView);
        getBinding().recyclerListView.setLayoutManager(new GridLayoutManager(getBinding().getRoot().getContext(), 1, GridLayoutManager.VERTICAL, false));
        getBinding().recyclerListView.setItemViewCacheSize(players.size());
        getBinding().recyclerListView.setHasFixedSize(true);

        SwipeController swipeControllerDelete = new SwipeController(true, getDrawable(android.R.drawable.ic_menu_delete), new SwipeControllerActions() {
            @Override
            public void onLeftSwiped(int position, View rootView, String message, View.OnClickListener onClickListener) {
                Player player = adapterRecyclerView.getModels().get(position);
                adapterRecyclerView.getModels().remove(player);
                getBinding().recyclerListView.setAdapter(new AdapterRecyclerView(getBinding().getRoot().getContext(), adapterRecyclerView.getModels()));
                getBinding().recyclerListView.smoothScrollToPosition(position);
                String str = "Deleted \n";
                str += "\tItem Name: " + player.getName();
                str += "\tItem Age: " + player.getAge().toString();
                str += "\tItem Rating: " + player.getRating().toString();
                super.onLeftSwiped(position, getBinding().rootRecyclerListView, str, v -> {
                    adapterRecyclerView.getModels().add(position, player);
                    getBinding().recyclerListView.setAdapter(new AdapterRecyclerView(getBinding().getRoot().getContext(), adapterRecyclerView.getModels()));
                    getBinding().recyclerListView.smoothScrollToPosition(position);
                });
            }
        });
        SwipeController swipeControllerEdit = new SwipeController(false, getDrawable(android.R.drawable.ic_menu_edit), new SwipeControllerActions() {
            @Override
            public void onRightSwiped(int position, View rootView, String message, View.OnClickListener onClickListener) {
                Player player = new Player("newBatdemir", "Turkey", "BatSpor", 100, 28);
                adapterRecyclerView.getModels().add(adapterRecyclerView.getItemCount(), player);
                getBinding().recyclerListView.setAdapter(new AdapterRecyclerView(getBinding().getRoot().getContext(), adapterRecyclerView.getModels()));
                getBinding().recyclerListView.smoothScrollToPosition(adapterRecyclerView.getItemCount());
                String str = "Inserted \n";
                str += "\tItem Name: " + player.getName();
                str += "\tItem Age: " + player.getAge().toString();
                str += "\tItem Rating: " + player.getRating().toString();
                super.onRightSwiped(position, getBinding().rootRecyclerListView, str, v -> {
                    adapterRecyclerView.getModels().remove(player);
                    getBinding().recyclerListView.setAdapter(new AdapterRecyclerView(getBinding().getRoot().getContext(), adapterRecyclerView.getModels()));
                    getBinding().recyclerListView.smoothScrollToPosition(position);
                });
            }
        });

        ItemTouchHelper itemTouchHelperDelete = new ItemTouchHelper(swipeControllerDelete);
        itemTouchHelperDelete.attachToRecyclerView(getBinding().recyclerListView);

        ItemTouchHelper itemTouchHelperEdit = new ItemTouchHelper(swipeControllerEdit);
        itemTouchHelperEdit.attachToRecyclerView(getBinding().recyclerListView);
    }
}
