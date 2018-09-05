package com.twu.biblioteca;

import java.util.Hashtable;
import java.util.Set;

public class BookManager {
    private Hashtable<LibraryBook, Boolean> libraryBookCollection;


    public BookManager(){
        this.libraryBookCollection = new Hashtable<>();
        libraryBookCollection.put(new LibraryBook("TDD", "Kent Beck", 2003),true);
        libraryBookCollection.put(new LibraryBook("NotAvailable", "Author", 0),false);
        libraryBookCollection.put(new LibraryBook("Harry Potter", "J K Rowling", 2000),false);
        libraryBookCollection.put(new LibraryBook("Java", "Kathy Sierra", 2001),false);
    }

    public boolean isBookInLibraryAvailable(LibraryBook libraryBook) {
        if(isBookInLibrary(libraryBook)){
            return libraryBookCollection.get(libraryBook);
        }
        return false;
    }


    public boolean changeAvailability(LibraryBook libraryBook, boolean availability) {
        boolean changed = false;
        if(isBookInLibrary(libraryBook)){
            if (isBookInLibraryAvailable(libraryBook) ^ availability){
                libraryBookCollection.put(libraryBook, availability);
                changed = true;
            }
        }
        return changed;
    }

    public void getAvailableBookDetails(String format) {
        Set<LibraryBook> keys = libraryBookCollection.keySet();
        for(LibraryBook key: keys) {
            if (isBookInLibraryAvailable(key)) {
                System.out.printf(format, key.bookDetails());
            }
        }
    }

    private boolean isBookInLibrary(LibraryBook libraryBook){
        return libraryBookCollection.containsKey(libraryBook);
    }

}
