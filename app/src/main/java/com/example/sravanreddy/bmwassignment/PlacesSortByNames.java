package com.example.sravanreddy.bmwassignment;

import java.util.Comparator;

public class PlacesSortByNames implements Comparator<Places> {
    @Override
    public int compare(Places places, Places t1) {
        return places.getName().compareTo(t1.getName());
    }
}
