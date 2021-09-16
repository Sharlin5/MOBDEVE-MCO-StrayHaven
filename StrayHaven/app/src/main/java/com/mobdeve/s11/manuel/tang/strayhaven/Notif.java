package com.mobdeve.s11.manuel.tang.strayhaven;

public class Notif {
    private String userKey;
    private String postKey;
    private String notifierName;
    private String notification;
    private String date;
    private String notifUrl;
    private String notifierKey;

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

    public Notif(String notifierName, String notifUrl, String notification, String date, String notifierKey){
        this.notifierName = notifierName;
        this.notifUrl = notifUrl;
        this.date = date;
        this.notification = notification;
        this.notifierKey = notifierKey;
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

    public String getNotifierKey() {
        return notifierKey;
    }

    public void setNotifierKey(String notifierKey) {
        this.notifierKey = notifierKey;
    }
}
