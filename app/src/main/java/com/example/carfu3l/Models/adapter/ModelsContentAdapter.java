package com.example.carfu3l.Models.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import com.example.carfu3l.DataModel.SingleContent;
import com.example.carfu3l.adapter.SingleContentAdapter;

import java.util.ArrayList;

public class ModelsContentAdapter extends SingleContentAdapter {

    public ModelsContentAdapter(ArrayList<SingleContent> dataset) {
        super(dataset);
    }

    @Override
    public void onBindViewHolder(@NonNull final SingleContentVH singleContentVH, int i) {
        super.onBindViewHolder(singleContentVH, i);
        singleContentVH.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemVal = singleContentVH.item.getText().toString();
                broadcastModelItemSelected(itemVal);
            }
        });
    }

    private void broadcastModelItemSelected(String itemVal) {
        Bundle b = new Bundle();
        b.putString("model", itemVal);

        Intent i = new Intent();
        i.putExtras(b);
        i.setAction("modelItemSelected");

        LocalBroadcastManager.getInstance(getVh().itemView.getContext()).sendBroadcast(i);
    }
}
