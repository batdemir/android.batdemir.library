package com.android.batdemir.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.android.batdemir.library.Models.AdapterRecyclerView;
import com.android.batdemir.library.Models.Player;
import com.android.batdemir.library.databinding.ActivityRecyclerBinding;
import com.android.batdemir.mylibrary.Components.MyAlertDialog;
import com.android.batdemir.mylibrary.Tools.SwipeTools.SwipeController;
import com.android.batdemir.mylibrary.Tools.SwipeTools.SwipeControllerActions;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity implements
        BaseActions,
        MyAlertDialog.AlertClickListener{

    //EXAMPLE OF RECYCLER VIEW WITH SWIPE
    private Context context;
    private ActivityRecyclerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getObjectReferences();
        loadData();
        setListeners();
    }

    @Override
    public void getObjectReferences() {
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_recycler);
    }

    @Override
    public void loadData() {
        setupRecyclerView();
    }

    @Override
    public void setListeners() {
        binding.btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gp = new Intent(context, MainActivity.class);
                startActivity(gp);
                finish();
            }
        });

        binding.btnShowAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAlertDialog.newInstance("Deneme",true,true).show(getSupportFragmentManager(),"this");
            }
        });
    }

    private ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(0, new Player("Batuhan", "Turkey", "BatSport", 90, 13));
        players.add(1, new Player("Batuhan1", "Turkey", "BatSport", 91, 12));
        players.add(2, new Player("Batuhan2", "Turkey", "BatSport", 92, 11));
        players.add(3, new Player("Batuhan3", "Turkey", "BatSport", 93, 29));
        players.add(4, new Player("Batuhan4", "Turkey", "BatSport", 94, 28));
        players.add(5, new Player("Batuhan5", "Turkey", "BatSport", 95, 27));
        players.add(6, new Player("Batuhan6", "Turkey", "BatSport", 96, 26));
        players.add(7, new Player("Batuhan7", "Turkey", "BatSport", 97, 25));
        players.add(8, new Player("Batuhan8", "Turkey", "BatSport", 98, 24));
        players.add(9, new Player("Batuhan9", "Turkey", "BatSport", 99, 23));
        players.add(10, new Player("Batuhan10", "Turkey", "BatSport", 81, 22));
        players.add(11, new Player("Batuhan11", "Turkey", "BatSport", 82, 21));
        players.add(12, new Player("Batuhan12", "Turkey", "BatSport", 83, 20));
        return players;
    }

    private void setupRecyclerView() {
        AdapterRecyclerView adapterRecyclerView = new AdapterRecyclerView(context, getPlayers());
        binding.recyclerListView.setAdapter(adapterRecyclerView);
        binding.recyclerListView.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
        binding.recyclerListView.setItemViewCacheSize(getPlayers().size());
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

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }
}
