package com.example.carfu3l.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.carfu3l.DataModel.MultipleContent;
import com.example.carfu3l.DataModel.SingleContent;
import com.example.carfu3l.R;

import java.util.ArrayList;

public abstract class MultipleContentAdapter extends RecyclerView.Adapter<MultipleContentAdapter.MultiContentVH> {

    private ArrayList<MultipleContent> dataset;
    private MultiContentVH vh;

    public MultipleContentAdapter(ArrayList<MultipleContent> dataset)
    {
        this.dataset = dataset;
    }

    public class MultiContentVH extends RecyclerView.ViewHolder {

        public TextView item1;
        public TextView item2;

        public MultiContentVH(@NonNull View itemView) {
            super(itemView);
            item1 = itemView.findViewById(R.id.item1);
            item2 = itemView.findViewById(R.id.item2);
        }

        public void bind(MultipleContent data)
        {
            item1.setText(data.getItem1());
            item2.setText(data.getItem2());
        }
    }

    @NonNull
    @Override
    public MultiContentVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RelativeLayout rl = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.multi_card_layout, viewGroup, false);
        vh = new MultiContentVH(rl);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MultiContentVH multiContentVH, int i) {
        multiContentVH.bind(dataset.get(i));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public MultiContentVH getVh() {
        return vh;
    }

    public ArrayList<MultipleContent> getDataset()
    {
        return dataset;
    }
}
