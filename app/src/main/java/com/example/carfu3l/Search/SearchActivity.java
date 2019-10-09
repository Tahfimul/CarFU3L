package com.example.carfu3l.Search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.carfu3l.Search.ViewModel.SearchVM;

public class SearchActivity extends AppCompatActivity {

    private SearchVM searchVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchVM = new SearchVM();

        String type = getIntent().getExtras().getString("type");
        String category = getIntent().getExtras().getString("category");

        searchVM.init(this);
    }

}
