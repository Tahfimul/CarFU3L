package com.example.carfu3l.ModelMakeSearch.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Toast;
import com.example.carfu3l.DataModel.MultipleContent;
import com.example.carfu3l.adapter.MultipleContentAdapter;

import java.util.ArrayList;

public class ModelContentAdapter extends MultipleContentAdapter {

    public ModelContentAdapter(ArrayList<MultipleContent> dataset) {
        super(dataset);
    }

    @Override
    public void onBindViewHolder(@NonNull final MultiContentVH multiContentVH, int i) {
        super.onBindViewHolder(multiContentVH, i);
        multiContentVH.item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model = multiContentVH.item1.getText().toString();
                sendModelItemSelectedBroadcast(model);
            }
        });
        multiContentVH.item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String make = multiContentVH.item2.getText().toString();
                sendMakeItemSelectedBroadcast(make);
            }
        });
    }

    private void sendModelItemSelectedBroadcast(String model) {
        Bundle b = new Bundle();
        b.putString("model", model);

        Intent i = new Intent();
        i.putExtras(b);
        i.setAction("modelItemSelected");

        LocalBroadcastManager.getInstance(getVh().itemView.getContext()).sendBroadcast(i);
    }

    private void sendMakeItemSelectedBroadcast(String make)
    {
        Bundle b = new Bundle();
        b.putString("make", make);

        Intent i = new Intent();
        i.putExtras(b);
        i.setAction("makeItemSelected");

        LocalBroadcastManager.getInstance(getVh().itemView.getContext()).sendBroadcast(i);
    }
}
