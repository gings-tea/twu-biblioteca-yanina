package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Hashtable;

public class BookManager {
    private Hashtable<Book, String> checkedOutBooks;
    private ArrayList<Book> availableBooks;


    public BookManager(){
        this.checkedOutBooks = new Hashtable<>();
        this.availableBooks = new ArrayList<>();

        availableBooks.add(new Book("TDD", "Kent Beck", 2003));

        checkedOutBooks.put(new Book("NotAvailable", "Author", 0),"111-1111");
        checkedOutBooks.put(new Book("Harry Potter", "J K Rowling", 2000),"111-1111");
        checkedOutBooks.put(new Book("Java", "Kathy Sierra", 2001),"111-1111");
    }

    public boolean isBookInLibraryAvailable(Book book) {
        return availableBooks.contains(book);
    }


    public boolean changeAvailability(Book book, boolean returning, String libraryId) {
        boolean changed;
        if(returning){
            changed = returnBook(book);
        } else {
            changed = checkOutBook(book,libraryId);
        }
        return changed;
    }

    public void getAvailableBookDetails(String format) {
        for(Book book: availableBooks) {
            if (isBookInLibraryAvailable(book)) {
                System.out.printf(format, book.bookDetails());
            }
        }
    }

    private boolean isBookInLibraryNotAvailable(Book book){
        return checkedOutBooks.containsKey(book);
    }

    private boolean returnBook(Book book) {
        if(isBookInLibraryNotAvailable(book)){
            checkedOutBooks.remove(book);
            availableBooks.add(book);
            return true;
        }
        return false;
    }

    private boolean checkOutBook(Book book, String libraryId) {
        if(isBookInLibraryAvailable(book)){
            availableBooks.remove(book);
            checkedOutBooks.put(book, libraryId);
            return true;
        }
        return false;
    }

}
