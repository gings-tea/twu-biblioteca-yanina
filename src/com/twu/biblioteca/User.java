package com.twu.biblioteca;

public class User {
    private String name;
    private String email;
    private String phone;
    private Credential credentials;

    public User(String name, String email, String phone, Credential credentials) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credentials = credentials;
    }
}
