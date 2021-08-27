package com.mobdeve.s11.manuel.tang.strayhaven;

public class Notif {
    private String profilename;
    private String notification;
    private int imageId;

    public Notif(String profilename, int imageId, String notification) {
        this.profilename = profilename;
        this.imageId = imageId;
        this.notification = notification;
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
}
