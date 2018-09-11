package com.twu.biblioteca.models;

import java.util.Arrays;

public class Credential {
    private String libraryNumber;
    private char[] password;

    public Credential(String libraryNumber, char[] password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        Credential otherCredential = (Credential) obj;
        return this.libraryNumber.equals(otherCredential.libraryNumber) &&
                Arrays.equals(this.password,otherCredential.password);
    }

    String getLibraryNumber() {
        return libraryNumber;
    }
}
