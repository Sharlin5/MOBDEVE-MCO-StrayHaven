package com.mobdeve.s11.manuel.tang.strayhaven;

public class Feed {
    private String postKey;
    private String posterKey;
    private String username;
    private String postUrl;
    private String profileUrl;
    private String caption;
    private String location;
    private String type;
    private String date;


    public Feed(String username, String type, String location, String caption) {
        this.username = username;
        this.type = type;
        this.location = location;
        this.caption = caption;
    }

    public Feed(String username, String type, String location, String caption, String date) {
        this.username = username;
        this.type = type;
        this.location = location;
        this.caption = caption;
        this.date = date;
    }

    public Feed(String posterKey, String postUrl, String type, String location, String caption, String date) {
        this.posterKey = posterKey;
        this.postUrl = postUrl;
        this.type = type;
        this.location = location;
        this.caption = caption;
        this.date = date;
    }

    public Feed(String username, String profileUrl, String postUrl, String type, String location, String caption, String date) {
        this.profileUrl = profileUrl;
        this.username = username;
        this.postUrl = postUrl;
        this.type = type;
        this.location = location;
        this.caption = caption;
        this.date = date;
    }

    public Feed(String posterkey, String username, String profileUrl, String postUrl, String type, String location, String caption, String date) {
        this.posterKey = posterkey;
        this.profileUrl = profileUrl;
        this.username = username;
        this.postUrl = postUrl;
        this.type = type;
        this.location = location;
        this.caption = caption;
        this.date = date;
    }

    public Feed(String postKey, String posterkey, String username, String profileUrl, String postUrl, String type, String location, String caption, String date) {
        this.postKey = postKey;
        this.posterKey = posterkey;
        this.profileUrl = profileUrl;
        this.username = username;
        this.postUrl = postUrl;
        this.type = type;
        this.location = location;
        this.caption = caption;
        this.date = date;
    }

    public String getUsername(){
        return this.username;
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

    public String getDate(){
        return this.date;
    }

    public String getPosterKey(){
        return this.posterKey;
    }

    public String getPostUrl(){return this.postUrl;}

    public String getProfileUrl(){
        return this.profileUrl;
    }

    public String getPostKey(){return this.postKey;}

    public void setPostKey(String postKey){
        this.postKey = postKey;
    }
}
