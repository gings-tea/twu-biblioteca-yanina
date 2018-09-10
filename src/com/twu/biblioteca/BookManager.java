package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Hashtable;

public class BookManager {
    private Hashtable<LibraryBook, String> checkedOutBooks;
    private ArrayList<LibraryBook> availableBooks;


    public BookManager(){
        this.checkedOutBooks = new Hashtable<>();
        this.availableBooks = new ArrayList<>();
        availableBooks.add(new LibraryBook("TDD", "Kent Beck", 2003));

        checkedOutBooks.put(new LibraryBook("NotAvailable", "Author", 0),"111-1111");
        checkedOutBooks.put(new LibraryBook("Harry Potter", "J K Rowling", 2000),"111-1111");
        checkedOutBooks.put(new LibraryBook("Java", "Kathy Sierra", 2001),"111-1111");
    }

    public boolean isBookInLibraryAvailable(LibraryBook libraryBook) {
        return availableBooks.contains(libraryBook);
    }


    public boolean changeAvailability(LibraryBook libraryBook, boolean availability, String libraryId) {
        boolean changed;
        if(availability){
            changed = returnBook(libraryBook);

        } else {
            changed = checkOutBook(libraryBook,libraryId);
        }
        return changed;
    }

    public void getAvailableBookDetails(String format) {
        for(LibraryBook book: availableBooks) {
            if (isBookInLibraryAvailable(book)) {
                System.out.printf(format, book.bookDetails());
            }
        }
    }

    private boolean isBookInLibraryNotAvailable(LibraryBook libraryBook){
        return checkedOutBooks.containsKey(libraryBook);
    }

    private boolean returnBook(LibraryBook libraryBook) {
        if(isBookInLibraryNotAvailable(libraryBook)){
            checkedOutBooks.remove(libraryBook);
            availableBooks.add(libraryBook);
            return true;
        }
        return false;
    }

    private boolean checkOutBook(LibraryBook libraryBook, String libraryId) {
        if(isBookInLibraryAvailable(libraryBook)){
            availableBooks.remove(libraryBook);
            checkedOutBooks.put(libraryBook, libraryId);
            return true;
        }
        return false;
    }

}
