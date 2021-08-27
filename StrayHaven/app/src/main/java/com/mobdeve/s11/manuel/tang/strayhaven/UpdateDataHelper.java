package com.mobdeve.s11.manuel.tang.strayhaven;

import java.util.ArrayList;

public class UpdateDataHelper {

    public ArrayList<Feed> loadUpdateData(){
        ArrayList<Feed> data = new ArrayList<Feed>();
        data.add(new Feed("Test1", R.drawable.picture_feature2, "Update", "Pasig City", "He's growing!"));
        data.add(new Feed("Test3", R.drawable.picture_feature1, "Update", "Caloocan City", "He's eating well"));
        data.add(new Feed("Test2", R.drawable.picture_feature3, "Update", "Pasay City", "Found a home for hime"));
        data.add(new Feed("Test1", R.drawable.picture_feature4, "Update", "Quezon City", "Look at him!"));
        return data;
    }
}
