package com.example.carfu3l.Search.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.carfu3l.R;
import com.example.carfu3l.Search.DataModel.Search;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchVH> {

    private ArrayList<Search> dataset;

    public SearchAdapter(ArrayList<Search> dataset)
    {
        this.dataset = dataset;
    }

    public class SearchVH extends RecyclerView.ViewHolder {

        private TextView textView;

        public SearchVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.val);
        }

        public void bind(Search data)
        {
            textView.setText(data.getData());
        }
    }

    @NonNull
    @Override
    public SearchAdapter.SearchVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RelativeLayout rv = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_card_layout, viewGroup, false);
        SearchVH vh = new SearchVH(rv);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchVH searchVH, int i) {

        searchVH.bind(dataset.get(i));

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
