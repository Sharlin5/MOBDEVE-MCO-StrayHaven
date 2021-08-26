package com.mobdeve.s11.manuel.tang.strayhaven;

public class Profile {
    private String username;
    private int imageid;
    private String location;
    private String description;

    public Profile(String username, int imageId, String location, String description) {
        this.username = username;
        this.imageid = imageId;
        this.location = location;
        this.description = description;
    }

    public String getUsername(){
        return this.username;
    }

    public int getImageId(){
        return this.imageid;
    }

    public String getCaption(){
        return this.description;
    }

    public String getLocation(){
        return this.location;
    }
}
