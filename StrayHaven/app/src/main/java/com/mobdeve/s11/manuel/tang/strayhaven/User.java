package com.mobdeve.s11.manuel.tang.strayhaven;

import android.graphics.Bitmap;

public class User {
    private String email;
    private String username;
    private String password;
    private String description;
    private String location;
    private String profilename;
   // private String profileKey;
    private String profilepicUrl;

    public User(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilename = username;
        this.description = " ";
        this.location = " ";
        this.profilepicUrl = " ";
    }


    public User(String email, String username, String profilename, String password, String description, String location){
        this.email = email;
        this.username = username;
        this.profilename = profilename;
        this.password = password;
        this.description = description;
        this.location = location;
    }

    public User(String email, String username, String profilename, String password, String description, String location, String profilepicUrl){
        this.email = email;
        this.username = username;
        this.profilename = profilename;
        this.password = password;
        this.description = description;
        this.location = location;
        this.profilepicUrl = profilepicUrl;
    }

    public String getEmail(){
        return this.email;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getDescription(){
        return this.description;
    }

    public String getLocation(){
        return this.location;
    }

    public String getProfilename(){
        return this.profilename;
    }
/*
    public String getProfileKey(){
        return this.profileKey;
    }

    public void setProfileKey(String key){
        this.profileKey = key;
    }
*/
    public String getProfilepicUrl(){
        return this.profilepicUrl;
    }

    public void setProfilepicUrl(String key){
        this.profilepicUrl = key;
    }
}
