package com.example.carfu3l.Search.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import com.example.carfu3l.DataModel.MultipleContent;
import com.example.carfu3l.DataModel.SingleContent;
import com.example.carfu3l.R;
import com.example.carfu3l.RecyclerAdapter;
import com.example.carfu3l.Search.Adapter.SearchAdapter;
import com.example.carfu3l.Search.DataModel.Search;
import com.example.carfu3l.Search.SearchActivity;
import com.example.carfu3l.Util.MainUtil;

import java.util.ArrayList;

public class SearchVM extends ViewModel {

    private ArrayList<Search> dataset = new ArrayList<>();

    private RecyclerView recyclerView;
    private SearchView sv;
    private SearchActivity c;

    public void setDataset(ArrayList<SingleContent> singleContents, ArrayList<MultipleContent> multipleContents)
    {
           for(SingleContent singleContent:singleContents)
           {
               Search sc = new Search(singleContent.getType(), singleContent.getData());
               dataset.add(sc);
           }

           for(MultipleContent multipleContent:multipleContents)
           {
               Search mc = new Search(multipleContent.getType(), multipleContent.getItem1());
               dataset.add(mc);
           }
    }

    public void init(SearchActivity context)
    {
        context.setContentView(R.layout.activity_search);
        recyclerView = context.findViewById(R.id.sRV);
        sv = context.findViewById(R.id.search_bar);
        c = context;

        initRecycler();
    }

    private void initRecycler()
    {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

        SearchAdapter adapter = new SearchAdapter(dataset);
        recyclerView.setAdapter(adapter);


    }
}
