package com.example.carfu3l.Models;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.example.carfu3l.DataModel.SingleContent;
import com.example.carfu3l.FuelEcon.FuelEconActivity;
import com.example.carfu3l.Models.ViewModel.ModelsVM;
import com.example.carfu3l.Models.adapter.ModelsContentAdapter;
import com.example.carfu3l.R;

import java.util.ArrayList;

public class ModelsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ModelsVM modelsVM;
    private String type;
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_models);
        recyclerView = findViewById(R.id.moRV);
        title = findViewById(R.id.title);
        initRecycler();

        type = getIntent().getExtras().getString("type");
        String make = getIntent().getExtras().getString("make");
        setTitle(make);
        initDataBinding(type, make);

        registerModelItemSelected();
    }

    private void initRecycler()
    {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void initDataBinding(String type, String make)
    {
        modelsVM = ViewModelProviders.of(this).get(ModelsVM.class);
        modelsVM.retrieveModels(type, make).observe(this, new Observer<ArrayList<SingleContent>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SingleContent> models) {

                ModelsContentAdapter adapter = new ModelsContentAdapter(models);

                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void registerModelItemSelected()
    {
        // Register to receive selected item from Models Adapter
        LocalBroadcastManager.getInstance(this).registerReceiver(modelItemSelectReceiver,
                new IntentFilter("modelItemSelected"));
    }

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "modelItemSelected"" is broadcast from ModelsContentAdapter
    private BroadcastReceiver modelItemSelectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String model = intent.getExtras().getString("model");
            showFuelEconActivity(model);
        }
    };

    private void showFuelEconActivity(String model)
    {
        Bundle b = new Bundle();
        b.putString("type", type);
        b.putString("model", model);

        Intent intent = new Intent(this, FuelEconActivity.class);
        intent.putExtras(b);

        startActivity(intent);
    }

    private void setTitle(String make)
    {
        title.setText("Showing "+ type + " models for "+make);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(modelItemSelectReceiver);
        super.onDestroy();
    }

}
