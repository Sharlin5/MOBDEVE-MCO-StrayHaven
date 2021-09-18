package com.mobdeve.s11.manuel.tang.strayhaven.settings;

import android.graphics.Bitmap;

public class User {
    private String email;
    private String username;
    private String password;
    private String description;
    private String location;
    private String profilename;
    private String profilepicUrl;
    private String featured1;
    private String featured2;
    private String featured3;
    private String featured4;
    private String featured5;


    public User(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilename = username;
        this.description = " ";
        this.location = " ";
        this.profilepicUrl = " ";
        this.featured1 = " ";
        this.featured2 = " ";
        this.featured3 = " ";
        this.featured4 = " ";
        this.featured5 = " ";
    }


    public User(String email, String username, String profilename, String password, String description, String location){
        this.email = email;
        this.username = username;
        this.profilename = profilename;
        this.password = password;
        this.description = description;
        this.location = location;
    }

    public User(String email, String username, String profilename, String password, String description, String location, String profilepicUrl,
                String featured1, String featured2, String featured3, String featured4, String featured5){
        this.email = email;
        this.username = username;
        this.profilename = profilename;
        this.password = password;
        this.description = description;
        this.location = location;
        this.profilepicUrl = profilepicUrl;
        this.featured1 = featured1;
        this.featured2 = featured2;
        this.featured3 = featured3;
        this.featured4 = featured4;
        this.featured5 = featured5;

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

    public String getProfilepicUrl(){
        return this.profilepicUrl;
    }

    public void setProfilepicUrl(String key){
        this.profilepicUrl = key;
    }

    public String getFeatured1() {
        return featured1;
    }

    public void setFeatured1(String featured1) {
        this.featured1 = featured1;
    }

    public String getFeatured2() {
        return featured2;
    }

    public void setFeatured2(String featured2) {
        this.featured2 = featured2;
    }

    public String getFeatured3() {
        return featured3;
    }

    public void setFeatured3(String featured3) {
        this.featured3 = featured3;
    }

    public String getFeatured4() {
        return featured4;
    }

    public void setFeatured4(String featured4) {
        this.featured4 = featured4;
    }

    public String getFeatured5() {
        return featured5;
    }

    public void setFeatured5(String featured5) {
        this.featured5 = featured5;
    }
}
