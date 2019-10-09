package com.example.carfu3l.ModelMakeSearch.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.ArrayAdapter;
import com.example.carfu3l.ModelMakeSearch.DataModel.ModelMake;
import com.example.carfu3l.ModelMakeSearch.LiveData.ItemsLiveData;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelMakeSearchVM extends ViewModel {

    public ArrayAdapter<String> getDeopdownAdapter(Context c)
    {
        String[] items = new String[]{"Makes", "Models"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, items);
        return adapter;
    }

    public MutableLiveData<ModelMake> retrieveItems(String item) {
        return new ItemsLiveData(item);
    }

}
