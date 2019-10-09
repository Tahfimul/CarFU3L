package com.example.carfu3l.Models.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.carfu3l.DataModel.SingleContent;
import com.example.carfu3l.ModelMakeSearch.DataModel.ModelMake;
import com.example.carfu3l.ModelMakeSearch.LiveData.ItemsLiveData;
import com.example.carfu3l.Models.LiveData.ModelsLiveData;

import java.util.ArrayList;

public class ModelsVM extends ViewModel {

    public MutableLiveData<ArrayList<SingleContent>> retrieveModels(String type, String make) {
        return new ModelsLiveData(type, make);
    }

}
