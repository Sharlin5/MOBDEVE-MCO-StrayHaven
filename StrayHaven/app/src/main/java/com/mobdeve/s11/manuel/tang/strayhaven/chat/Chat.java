package com.mobdeve.s11.manuel.tang.strayhaven.chat;

public class Chat {

    private String sender;
    private String receiver;
    private String message;
    private String profilePic;

    public Chat(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public Chat(String sender, String receiver, String message, String profilePic) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.profilePic = profilePic;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePic() {
        return profilePic;
    }
}
