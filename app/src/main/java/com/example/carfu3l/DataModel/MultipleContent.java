package com.example.carfu3l.DataModel;

public class MultipleContent {

    public static final int MODEL_ITEM = 1;

    private String item1;
    private String item2;
    private int type;

    public MultipleContent(String item1, String item2, int type)
    {
        this.item1 = item1;
        this.item2 = item2;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem2() {
        return item2;
    }

    public int getType()
    {
        return type;
    };

    public void setType(int type)
    {
        this.type = type;
    };
}
