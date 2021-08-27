package com.mobdeve.s11.manuel.tang.strayhaven;

public class Notif {
    private String profilename;
    private String notification;
    private int imageId;
    private CustomDate customDate;

    public Notif(String profilename, int imageId, String notification, CustomDate date) {
        this.profilename = profilename;
        this.imageId = imageId;
        this.notification = notification;
        this.customDate = date;
    }

    public String getProfilename(){
        return this.profilename;
    }

    public int getImageId(){
        return this.imageId;
    }

    public String getNotification(){
        return this.notification;
    }

    public CustomDate getCustomDate(){
        return this.customDate;
    }
}
