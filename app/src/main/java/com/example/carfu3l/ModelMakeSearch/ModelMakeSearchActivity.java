package com.example.carfu3l.ModelMakeSearch;

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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import com.example.carfu3l.DataModel.SingleContent;
import com.example.carfu3l.FuelEcon.FuelEconActivity;
import com.example.carfu3l.ModelMakeSearch.Adapter.ModelContentAdapter;
import com.example.carfu3l.Models.ModelsActivity;
import com.example.carfu3l.ModelMakeSearch.Adapter.MakeContentAdapter;
import com.example.carfu3l.Search.SearchActivity;
import com.example.carfu3l.Search.ViewModel.SearchVM;
import com.example.carfu3l.adapter.MultipleContentAdapter;
import com.example.carfu3l.ModelMakeSearch.DataModel.ModelMake;
import com.example.carfu3l.ModelMakeSearch.ViewModel.ModelMakeSearchVM;
import com.example.carfu3l.R;

import java.util.ArrayList;

public class ModelMakeSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ModelMakeSearchVM moMkSearchVM;
    private Spinner dropdown;
    private RecyclerView recyclerView;
    private TextView titile;
    private ImageView searchBtn;
    private MakeContentAdapter makeContentAdapter;
    private ModelContentAdapter modelContentAdapter;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_make_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dropdown = findViewById(R.id.dropdown);

        recyclerView = findViewById(R.id.moMkRV);

        titile = findViewById(R.id.title);

        searchBtn = findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(this);

        type = getIntent().getExtras().getString("item");

        initDataBinding(type);
        initDropdown();
        initRecyclerView();
        registerMakeItemSelected();
        registerModelItemSelcted();

    }

    private void initDataBinding(String item)
    {
        moMkSearchVM = ViewModelProviders.of(this).get(ModelMakeSearchVM.class);
        moMkSearchVM.retrieveItems(item).observe(this, new Observer<ModelMake>() {
            @Override
            public void onChanged(@Nullable ModelMake modelMake) {

              makeContentAdapter = new MakeContentAdapter(modelMake.getSingleContents());
              modelContentAdapter = new ModelContentAdapter(modelMake.getMultipleContents());

              if(recyclerView.getAdapter()!=null)
              {
                  if (recyclerView.getAdapter().toString().contains("MakeContentAdapter"))
                      dropdown.setSelection(0);
                  else
                      dropdown.setSelection(1);
              }
              else {
                  setMakeTitle();
                  recyclerView.setAdapter(makeContentAdapter);
              }
            }
        });

    }

    private void initDropdown()
    {
        dropdown.setAdapter(moMkSearchVM.getDeopdownAdapter(this));
        dropdown.setOnItemSelectedListener(this);
    }

    private void initRecyclerView()
    {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    //Dropdown Listener Stuff
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 0:
                setMakeTitle();
                if (makeContentAdapter!=null)
                    recyclerView.setAdapter(makeContentAdapter);
                break;
            case 1:
                setModelTitle();
                if (modelContentAdapter!=null)
                    recyclerView.setAdapter(modelContentAdapter);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    ////////////////////////////////////

    private void registerMakeItemSelected()
    {
        // Register to receive selected item from Make Content Adapter
        LocalBroadcastManager.getInstance(this).registerReceiver(makeItemSelectReceiver,
                new IntentFilter("makeItemSelected"));
    }

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "makeItemSelected"" is broadcast from MakeContentAdapter
    private BroadcastReceiver makeItemSelectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String make = intent.getExtras().getString("make");
            System.out.println("Showing models for make "+make);
            showMakeModelsActivity(make);
        }
    };

    private void showMakeModelsActivity(String make) {
        Bundle b = new Bundle();
        b.putString("type", type);
        b.putString("make", make);

        Intent intent = new Intent(this, ModelsActivity.class);
        intent.putExtras(b);

        startActivity(intent);

    }

    private void registerModelItemSelcted() {
        // Register to receive selected item from Model Content Adapter
        LocalBroadcastManager.getInstance(this).registerReceiver(modelItemSelectReceiver,
                new IntentFilter("modelItemSelected"));
    }

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "modelItemSelected"" is broadcast from MakeContentAdapter
    private BroadcastReceiver modelItemSelectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String model = intent.getExtras().getString("model");
            showFuelEconActivity(model);
        }
    };

    private void showFuelEconActivity(String model) {
        Bundle b = new Bundle();
        b.putString("type", type);
        b.putString("model", model);

        Intent intent = new Intent(this, FuelEconActivity.class);
        intent.putExtras(b);

        startActivity(intent);
    }

    private void setMakeTitle()
    {
        titile.setText("Select a " + type + " make");
    }

    private void setModelTitle()
    {
        titile.setText("Select a " + type + " model");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerMakeItemSelected();
        registerModelItemSelcted();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(makeItemSelectReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(modelItemSelectReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(makeItemSelectReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(modelItemSelectReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.search_btn)
        {
            Bundle b = new Bundle();
            b.putString("type", type);
            if (dropdown.getSelectedItemPosition()==0)
                b.putString("category", "make");
            else
                b.putString("category", "model");

            Intent i = new Intent(this, SearchActivity.class);
            i.putExtras(b);

            startActivity(i);
        }
    }


}
