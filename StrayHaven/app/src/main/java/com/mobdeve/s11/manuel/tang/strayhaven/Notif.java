package com.mobdeve.s11.manuel.tang.strayhaven;

public class Notif {
    private String userKey;
    private String postKey;
    private String notifierName;
    private String notification;
    private String date;
    private String notifUrl;
/*
    public Notif(String profilename, int imageId, String notification, CustomDate date) {
        this.profilname = profilename;
        this.notification = notification;
        this.customDate = date;
    }
*/
    public Notif(String notifierName, String notifUrl, String date){
        this.notifierName = notifierName;
        this.notifUrl = notifUrl;
        this.date = date;
    }

    public Notif(String notifierName, String notifUrl, String notification, String date){
        this.notifierName = notifierName;
        this.notifUrl = notifUrl;
        this.date = date;
        this.notification = notification;
    }


    public String getNotifierName() {
        return notifierName;
    }

    public String getNotification(){
        return this.notification;
    }

    public String getDate() {
        return date;
    }

    public String getPostKey() {
        return postKey;
    }

    public String getNotifUrl() {
        return notifUrl;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNotifUrl(String notifUrl) {
        this.notifUrl = notifUrl;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
