package com.example.carfu3l.ModelMakeSearch.DataModel;

import com.example.carfu3l.DataModel.MultipleContent;
import com.example.carfu3l.DataModel.SingleContent;

import java.util.ArrayList;

public class ModelMake {

    private ArrayList<SingleContent> singleContents;
    private ArrayList<MultipleContent> multipleContents;

    public void setSingleContents(ArrayList<SingleContent> singleContents) {
        this.singleContents = singleContents;
    }

    public ArrayList<SingleContent> getSingleContents() {
        return singleContents;
    }

    public void setMultipleContents(ArrayList<MultipleContent> multipleContents) {
        this.multipleContents = multipleContents;
    }

    public ArrayList<MultipleContent> getMultipleContents() {
        return multipleContents;
    }
}
