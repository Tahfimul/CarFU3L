package com.example.carfu3l.Models.LiveData;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.carfu3l.DataModel.SingleContent;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class ModelsLiveData extends MutableLiveData<ArrayList<SingleContent>> {

    private ArrayList<SingleContent> dataset = new ArrayList<>();

    private DatabaseReference mModelsRef;

    public ModelsLiveData(String type, String make)
    {
        mModelsRef =  FirebaseDatabase.getInstance().getReference(type+"/MakeModels/"+make);
    }

    @Override
    protected void onActive() {
        super.onActive();
        mModelsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot model:dataSnapshot.getChildren())
                {
                    SingleContent mo = new SingleContent(model.getValue().toString(), SingleContent.MAKE_ITEM);
                    dataset.add(mo);
                }

                setValue(dataset);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
