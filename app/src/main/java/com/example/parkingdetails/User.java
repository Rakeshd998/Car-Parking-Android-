package com.example.parkingdetails;

public class User {
    private long id;
    private String name;
    private String phone;

    public User() {
        // Default constructor
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Getters and setters for User
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

