package com.mobdeve.s11.manuel.tang.strayhaven;

import java.util.ArrayList;

public class TrackerDataHelper {

    public ArrayList<Tracker> loadTrackerData(){
        ArrayList<Tracker> data = new ArrayList<Tracker>();
        data.add(new Tracker("Foster", R.drawable.picture_feature1, new CustomDate(2021, 0,10)));
        data.add(new Tracker("Foster", R.drawable.picture_feature4, new CustomDate(2021, 4,11)));
        data.add(new Tracker("Foster", R.drawable.picture_feature3, new CustomDate(2021, 9,20)));
        data.add(new Tracker("Foster", R.drawable.picture_feature2, new CustomDate(2021, 0,13)));
        return data;
    }

}
