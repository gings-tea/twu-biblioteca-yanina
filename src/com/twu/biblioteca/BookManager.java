package com.twu.biblioteca;

import java.util.Hashtable;
import java.util.Set;

public class BookManager {
    private Hashtable<LibraryBook, Boolean> libraryBookCollection;

    public BookManager(Hashtable<LibraryBook, Boolean> libraryBookCollection) {
        this.libraryBookCollection = libraryBookCollection;
    }

    public boolean isBookInLibraryAvailable(LibraryBook libraryBook) {
        if(isBookInLibrary(libraryBook)){
            return libraryBookCollection.get(libraryBook);
        }
        return false;
    }

    public boolean isBookInLibrary(LibraryBook libraryBook){
        return libraryBookCollection.containsKey(libraryBook);
    }


    public void changeAvailability(LibraryBook libraryBook, boolean availability) {
        if(isBookInLibrary(libraryBook)){
            libraryBookCollection.put(libraryBook, availability);
        }
    }

    public void getAvailableBookDetails(String format) {
        Set<LibraryBook> keys = libraryBookCollection.keySet();
        for(LibraryBook key: keys) {
            if (isBookInLibraryAvailable(key)) {
                System.out.printf(format, key.bookDetails());
            }
        }
    }

}
