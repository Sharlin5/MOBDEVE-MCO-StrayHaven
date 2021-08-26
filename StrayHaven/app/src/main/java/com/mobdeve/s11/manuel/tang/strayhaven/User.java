package com.mobdeve.s11.manuel.tang.strayhaven;

public class User {
    private String email;
    private String username;
    private String password;
    private String description;
    private String location;
    private String profilename;

    public User(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilename = username;
        this.description = " ";
        this.location = " ";
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
}
