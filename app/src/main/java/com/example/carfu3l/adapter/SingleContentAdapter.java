package com.example.carfu3l.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.carfu3l.DataModel.SingleContent;
import com.example.carfu3l.R;

import java.util.ArrayList;

public abstract class SingleContentAdapter extends RecyclerView.Adapter<SingleContentAdapter.SingleContentVH> {

    private ArrayList<SingleContent> dataset;
    private SingleContentVH vh;

    public SingleContentAdapter(ArrayList<SingleContent> dataset)
    {
        this.dataset = dataset;
    }


    public class SingleContentVH extends RecyclerView.ViewHolder {

        public TextView item;

        public SingleContentVH(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.val);
        }

        public void bind(SingleContent item)
        {
            this.item.setText(item.getData());
        }
    }

    @NonNull
    @Override
    public SingleContentVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RelativeLayout rv = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_card_layout, viewGroup, false);
        vh = new SingleContentVH(rv);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SingleContentVH singleContentVH, int i) {
        singleContentVH.bind(dataset.get(i));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public SingleContentVH getVh() {
        return vh;
    }

    public ArrayList<SingleContent> getDataset()
    {
        return dataset;
    }
}
