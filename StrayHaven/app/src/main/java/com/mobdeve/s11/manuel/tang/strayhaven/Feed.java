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
    private String isDone;

    public Feed(String username, String profileUrl, String postUrl, String type, String location, String caption, String date) {
        this.profileUrl = profileUrl;
        this.username = username;
        this.postUrl = postUrl;
        this.type = type;
        this.location = location;
        this.caption = caption;
        this.date = date;
    }

    // for update
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

    // for tracker/request
    public Feed(String posterkey, String username, String profileUrl, String postUrl, String type, String location, String caption, String date, String isDone){
        this.posterKey = posterkey;
        this.profileUrl = profileUrl;
        this.username = username;
        this.postUrl = postUrl;
        this.type = type;
        this.location = location;
        this.caption = caption;
        this.date = date;
        this.isDone = isDone;
    }

    //for delete
    public Feed(String postKey, String posterkey, String username, String profileUrl, String postUrl, String type, String location, String caption, String date, String isDone){
        this.postKey = postKey;
        this.posterKey = posterkey;
        this.profileUrl = profileUrl;
        this.username = username;
        this.postUrl = postUrl;
        this.type = type;
        this.location = location;
        this.caption = caption;
        this.date = date;
        this.isDone = isDone;
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

    public String getIsDone(){
        return this.isDone;
    }
}
