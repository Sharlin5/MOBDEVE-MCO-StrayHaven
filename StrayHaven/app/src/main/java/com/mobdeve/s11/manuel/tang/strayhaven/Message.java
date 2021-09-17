package com.mobdeve.s11.manuel.tang.strayhaven;

public class Message {
    private String username,profilename, imageId, userKey;

    public Message(String username, String profilename, String imageId, String userKey) {
        this.username = username;
        this.imageId = imageId;
        this.profilename = profilename;
        this.userKey = userKey;
    }

    public String getUsername(){
        return this.username;
    }

    public String getProfilename(){
        return this.profilename;
    }

    public String getImageId(){
        return this.imageId;
    }

    public String getUserKey() {
        return userKey;
    }
}
