package com.mobdeve.s11.manuel.tang.strayhaven;

import java.util.ArrayList;

public class NotificationDataHelper {

    public ArrayList<Notif> loadNotifData(){
        ArrayList<Notif> data = new ArrayList<Notif>();
        data.add(new Notif("Sharlin", R.drawable.picture_feature4, "Helloo", new CustomDate(2021, 0,10)));
        data.add(new Notif("Aika", R.drawable.picture_feature2, "Helloo", new CustomDate(2021, 1,15)));
        data.add(new Notif("Alyssa", R.drawable.picture_feature1, "Helloo", new CustomDate(2021, 1,20)));
        data.add(new Notif("Angelo", R.drawable.picture_feature3, "Helloo", new CustomDate(2021, 1,20)));

        return data;
    }

}
