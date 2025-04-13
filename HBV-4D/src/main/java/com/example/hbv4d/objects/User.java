package com.example.hbv4d.objects;

public class User {
    private final int id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private static User loggedIn;

    public User(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setLoggedIn(User user){
        loggedIn = user ;
    }

    public static User getLoggedIn(){
        return loggedIn;
    }

}
