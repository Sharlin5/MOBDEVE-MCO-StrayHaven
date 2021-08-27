package com.mobdeve.s11.manuel.tang.strayhaven;

import java.util.ArrayList;

public class NotificationDataHelper {

    public ArrayList<Notif> loadNotifData(){
        ArrayList<Notif> data = new ArrayList<Notif>();
        data.add(new Notif("Sharlin", R.drawable.picture_feature4, "Helloo"));
        data.add(new Notif("Aika", R.drawable.picture_feature2, "Helloo"));
        data.add(new Notif("Alyssa", R.drawable.picture_feature1, "Helloo"));
        data.add(new Notif("Angelo", R.drawable.picture_feature3, "Helloo"));

        return data;
    }

}
