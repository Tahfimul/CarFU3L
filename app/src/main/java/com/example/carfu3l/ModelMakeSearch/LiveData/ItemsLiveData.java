package com.example.carfu3l.ModelMakeSearch.LiveData;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.carfu3l.ModelMakeSearch.DataModel.ModelMake;
import com.example.carfu3l.DataModel.MultipleContent;
import com.example.carfu3l.DataModel.SingleContent;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class ItemsLiveData extends MutableLiveData<ModelMake> {

    private DatabaseReference mModelsRef;
    private DatabaseReference mMakesRef;

    private ModelMake dataset = new ModelMake();

    public ItemsLiveData(String item)
    {
        mModelsRef =  FirebaseDatabase.getInstance().getReference(item+"/Models");
        mMakesRef = FirebaseDatabase.getInstance().getReference(item+"/Makes");
    }

    @Override
    protected void onActive() {
        super.onActive();
        mModelsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MultipleContent> models = new ArrayList<>();

                for (DataSnapshot model:dataSnapshot.getChildren())
                {
                    MultipleContent mo = new MultipleContent(model.getKey(), model.child("Make").getValue().toString(), MultipleContent.MODEL_ITEM);
                    models.add(mo);
                }

                dataset.setMultipleContents(models);

                getMakes();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getMakes()
    {
        mMakesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<SingleContent> makes = new ArrayList<>();

                for (DataSnapshot make:dataSnapshot.getChildren())
                {
                    SingleContent mk = new SingleContent(make.getValue().toString(), SingleContent.MAKE_ITEM);
                    makes.add(mk);
                }

                dataset.setSingleContents(makes);
                setValue(dataset);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
