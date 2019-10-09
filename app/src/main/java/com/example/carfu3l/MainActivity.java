package com.example.carfu3l;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.carfu3l.ModelMakeSearch.ModelMakeSearchActivity;
import com.example.carfu3l.Util.MainUtil;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.mainRV);

        init();
    }

    private void init() {
        initRecyclerView();
        registerItemSelect();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(MainUtil.getDataset());
        recyclerView.setAdapter(adapter);
    }

    private void registerItemSelect()
    {
        // Register to receive selected item from RecyclerAdapter VH
        LocalBroadcastManager.getInstance(this).registerReceiver(itemSelectReceiver,
                new IntentFilter("selected"));
    }

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "selected" is broadcast from RecyclerAdapter VH
    private BroadcastReceiver itemSelectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String item = intent.getExtras().getString("item");
            showItemActivity(item);
        }
    };

    private void showItemActivity(String item)
    {
        Bundle b = new Bundle();
        b.putString("item", item);

        Intent intent = new Intent(this, ModelMakeSearchActivity.class);
        intent.putExtras(b);

        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(itemSelectReceiver);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(itemSelectReceiver);
        super.onBackPressed();
    }
}
