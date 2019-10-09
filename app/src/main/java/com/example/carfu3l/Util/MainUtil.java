package com.example.carfu3l.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainUtil {

    public static List<String> getDataset()
    {
        String types[] = {"Cars", "Motorcycles", "Trucks", "Atvs"};
        List<String> dataset = Arrays.asList(types);
        return dataset;
    }



}
