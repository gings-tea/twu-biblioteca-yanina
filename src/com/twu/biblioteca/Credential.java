package com.twu.biblioteca;

public class Credential {
    private String libraryNumber;
    private char[] password;

    public Credential(String libraryNumber, char[] password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }
}
