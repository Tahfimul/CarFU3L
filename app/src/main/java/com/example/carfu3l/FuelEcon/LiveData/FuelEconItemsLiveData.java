package com.example.carfu3l.FuelEcon.LiveData;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.carfu3l.DataModel.MultipleContent;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class FuelEconItemsLiveData extends MutableLiveData<ArrayList<MultipleContent>> {

    private ArrayList<MultipleContent> dataset = new ArrayList<>();
    private DatabaseReference mFuelEconRef;

    public FuelEconItemsLiveData(String type, String model)
    {
        mFuelEconRef =  FirebaseDatabase.getInstance().getReference(type+"/FuelEcon/"+model);
    }

    @Override
    protected void onActive() {
        super.onActive();
        mFuelEconRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot year:dataSnapshot.getChildren())
                {
                    MultipleContent fuelEcon = new MultipleContent(year.child("FuelEcon").getValue().toString(), year.getKey(), MultipleContent.MODEL_ITEM);
                    dataset.add(fuelEcon);
                }

                setValue(dataset);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
