package com.example.carfu3l.FuelEcon.VIewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.carfu3l.DataModel.MultipleContent;
import com.example.carfu3l.FuelEcon.LiveData.FuelEconItemsLiveData;

import java.util.ArrayList;

public class FuelEconVM extends ViewModel {

    public MutableLiveData<ArrayList<MultipleContent>> retrieveFuelEconByYear(String type, String model)
    {
        return new FuelEconItemsLiveData(type, model);
    }
}
