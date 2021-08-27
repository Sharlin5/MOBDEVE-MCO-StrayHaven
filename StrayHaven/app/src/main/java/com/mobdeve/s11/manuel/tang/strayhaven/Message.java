package com.mobdeve.s11.manuel.tang.strayhaven;

public class Message {
    private String username, profilename;
    private int imageId;

    public Message(String username, String profilename, int imageId) {
        this.username = username;
        this.imageId = imageId;
        this.profilename = profilename;
    }

    public String getUsername(){
        return this.username;
    }

    public String getProfilename(){
        return this.profilename;
    }

    public int getImageId(){
        return this.imageId;
    }


}
