package com.mobdeve.s11.manuel.tang.strayhaven;

public class Feed {
    private String username;
    private int imageId;
    private String caption;
    private String location;
    private String type;

    public Feed(String username, int imageId, String type, String location, String caption) {
        this.username = username;
        this.imageId = imageId;
        this.type = type;
        this.location = location;
        this.caption = caption;
    }

    public String getUsername(){
        return this.username;
    }

    public int getImageId(){
        return this.imageId;
    }

    public String getCaption(){
        return this.caption;
    }

    public String getLocation(){
        return this.location;
    }

    public String getType(){
        return this.type;
    }
}
