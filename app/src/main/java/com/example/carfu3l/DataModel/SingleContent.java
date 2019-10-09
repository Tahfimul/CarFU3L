package com.example.carfu3l.DataModel;

public class SingleContent {

    public static final int MAKE_ITEM = 0;

    private String data;
    private int type;

    public SingleContent(String data, int type)
    {
        this.data = data;
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public int getType()
    {
     return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }
}
