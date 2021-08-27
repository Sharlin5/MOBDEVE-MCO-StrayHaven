package com.mobdeve.s11.manuel.tang.strayhaven;

import java.util.ArrayList;

public class FeedDataHelper {

    public ArrayList<Feed> loadFeedData(){
        ArrayList<Feed> data = new ArrayList<Feed>();
        data.add(new Feed("Mary", R.drawable.picture_feature1, "Foster", "Manila City", "Foster this little one."));
        data.add(new Feed("Dan", R.drawable.picture_feature2, "Foster", "Valenzuela City", "Found this guy in Valenzuela."));
        data.add(new Feed("Lucy", R.drawable.picture_feature3, "Adopt", "Pasay City", "Adopt this little one."));
        data.add(new Feed("Sara", R.drawable.picture_feature4, "Foster", "Laguna City", "Ohh found a cute pet."));
        return data;
    }

}
