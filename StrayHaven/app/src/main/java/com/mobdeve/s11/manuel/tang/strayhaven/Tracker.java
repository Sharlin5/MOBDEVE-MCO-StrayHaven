package com.mobdeve.s11.manuel.tang.strayhaven;

public class Tracker {
    private String posttype;
    private CustomDate date;
    private int imageId;

    public Tracker(String posttype, int imageId, CustomDate date) {
        this.posttype = posttype;
        this.imageId = imageId;
        this.date = date;
    }

    public String getPosttype(){
        return this.posttype;
    }

    public int getImageId(){
        return this.imageId;
    }

    public CustomDate getDate(){
        return this.date;
    }
}
