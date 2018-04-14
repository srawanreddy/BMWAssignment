package com.example.sravanreddy.bmwassignment;

import java.util.Comparator;

public class PlacesSortByAddress implements Comparator<Places> {

    @Override
    public int compare(Places places, Places t1) {
        return places.getAddress().compareTo(t1.getAddress());
    }
}
