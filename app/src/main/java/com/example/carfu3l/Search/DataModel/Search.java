package com.example.carfu3l.Search.DataModel;

public class Search {

    private int type;
    private String data;

    public Search(int type, String data)
    {
        this.type = type;
        this.data = data;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
