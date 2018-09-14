package com.twu.biblioteca.models;

public abstract class AbstractLibraryItem {
    public String type;

    public boolean isSameType(String type) {
        return this.type.equals(type);
    }

    public abstract String[] getDetails();
}
