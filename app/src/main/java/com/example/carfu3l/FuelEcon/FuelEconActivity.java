package com.example.carfu3l.FuelEcon;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.example.carfu3l.DataModel.MultipleContent;
import com.example.carfu3l.DataModel.SingleContent;
import com.example.carfu3l.FuelEcon.VIewModel.FuelEconVM;
import com.example.carfu3l.FuelEcon.adapter.FuelEconContentAdapter;
import com.example.carfu3l.Models.ViewModel.ModelsVM;
import com.example.carfu3l.Models.adapter.ModelsContentAdapter;
import com.example.carfu3l.R;

import java.util.ArrayList;

public class FuelEconActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FuelEconVM fuelEconVM;
    private String type;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_econ);

        recyclerView = findViewById(R.id.fuelRV);

        title = findViewById(R.id.title);

        initRecycler();

        type = getIntent().getExtras().getString("type");
        String model = getIntent().getExtras().getString("model");
        setTitle(model);
        initDataBinding(model);
    }

    private void initRecycler() {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initDataBinding(String model)
    {
        fuelEconVM = ViewModelProviders.of(this).get(FuelEconVM.class);
        fuelEconVM.retrieveFuelEconByYear(type, model).observe(this, new Observer<ArrayList<MultipleContent>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MultipleContent> fuelEconData) {
                FuelEconContentAdapter adapter = new FuelEconContentAdapter(fuelEconData);

                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void setTitle(String model)
    {
        title.setText("Showing fuel econ data for "+model+" "+type);
    }
}
