package com.example.carfu3l;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {


    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv  = itemView.findViewById(R.id.val);
        }

        public void bind(String val)
        {
            tv.setText(val);

            tv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.val:
                    sendSelectBroadcast();
                    break;
            }
        }

        //Send Select Broadcast Message to MainActivity
        private void sendSelectBroadcast() {
            Bundle bundle = new Bundle();
            bundle.putString("item", tv.getText().toString());

            Intent i = new Intent();
            i.setAction("selected");
            i.putExtras(bundle);

            LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(i);
        }
    }


    private List<String> dataset;

    public RecyclerAdapter(List<String> dataset)
    {
        System.out.println(dataset.toString() +" dataset in RecyclerAdapter");
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RelativeLayout v = (RelativeLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_card_layout, viewGroup, false);

        VH vh = new VH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.VH vh, int i) {
        vh.bind(dataset.get(i));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
