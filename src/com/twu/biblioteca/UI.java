package com.twu.biblioteca;

import java.util.Scanner;

public class UI {
    private String welcomeMessage = "*** WELCOME TO THE TWU LIBRARY ***";
    private String optionList = "1) List all books\n2) Check out book\n3) Return book\n4) Quit\nPlease select an option: ";
    private String format = "%-10s  %-25s  %-5s \n";
    private String returnOkMsg = "Thank you for returning the book";
    private String returnErrorMsg = "That is not a valid book to return.";
    private String checkOutOkMsg = "Thank you! Enjoy the book";
    private String checkOutErrorMsg = "That book is not available.";
    BookManager bookManager;

    public UI() {
        DBManager dbManager = new DBManager();
        bookManager = new BookManager(dbManager.getDB());
    }

    public void welcomeMessagePrinter(){
        System.out.println(welcomeMessage);
    }

    public void optionManager(){
        Scanner sc = new Scanner(System.in);
        int optionSelected;
        LibraryBook libraryBook;

        do{
            System.out.print(optionList);
            optionSelected = Integer.valueOf(sc.nextLine());
            switch(optionSelected){
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    libraryBook = enterLibraryBookInformation(sc);
                    checkOutBook(libraryBook, false, checkOutOkMsg, checkOutErrorMsg);
                    break;
                case 3:
                    libraryBook = enterLibraryBookInformation(sc);
                    makeAvailableAReturnedBook(libraryBook, true, returnOkMsg, returnErrorMsg);
                    break;
                case 4:
                    break;
                default:
                    System.out.println();
                    System.out.println("Select a valid option!");
                    System.out.println();
            }
        } while(optionSelected != 4);
    }


    public boolean checkOutBook(LibraryBook libraryBook, boolean availability, String successMsg, String errorMsg) {
        boolean checkOut = true;
        if (bookManager.changeAvailability(libraryBook, availability)){
            System.out.println(successMsg);
        } else {
            System.out.println(errorMsg);
            checkOut = false;
        }
        return checkOut;
    }

    public boolean makeAvailableAReturnedBook(LibraryBook libraryBook, boolean availability, String successMsg, String errorMsg) {
        boolean returned = true;
        if(bookManager.changeAvailability(libraryBook, availability)) {
            System.out.println(successMsg);
        } else {
            System.out.println(errorMsg);
            returned = false;
        }
        return returned;
    }

    private LibraryBook enterLibraryBookInformation(Scanner sc){
        System.out.print("Please enter the book title: ");
        String title = sc.nextLine();
        System.out.print("Please enter the book author/s: ");
        String author = sc.nextLine();
        System.out.print("Please enter the book year of publication: ");
        int yearPublished = Integer.valueOf(sc.nextLine());
        return new LibraryBook(title, author, yearPublished);
    }

    private void listAllBooks(){
        System.out.println("\nAvailable books");
        System.out.printf(format,"Title","Author","Year Published");
        bookManager.getAvailableBookDetails(format);
        System.out.println();
    }
}
