package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.Scanner;

class UI {
    private String welcomeMessage = "*** WELCOME TO THE TWU LIBRARY ***";
    private String notLoggedOptions = "\n1) List all books\n2) List all movies\n3) Check out movie\n4) Log In\n5) Quit\nPlease select an option: ";
    private String loggedOptions = "\n1) List all books\n2) Check out book\n3) Return book\n4) List all movies\n5) Check out movie\n6) Log Out\n7) Quit\nPlease select an option: ";
    private String format = "%-10s  %-25s  %-5s \n";
    private String returnOkMsg = "Thank you for returning the book";
    private String returnErrorMsg = "That is not a valid book to return.";
    private String checkOutOkMsg = "Thank you! Enjoy the book";
    private String checkOutErrorMsg = "That book is not available.";
    private boolean isLogged;
    BookManager bookManager;

    UI() {
        bookManager = new BookManager();
        isLogged = false;
    }

    void welcomeMessagePrinter(){
        System.out.println(welcomeMessage);
    }

    void optionManager(){
        Scanner sc = new Scanner(System.in);
        boolean didNotQuit;
        do{
            if(isLogged){
                didNotQuit = loggedCase(sc);
            }
            else{
                didNotQuit = notLoggedCase(sc);
            }
        } while(didNotQuit);
    }

    private boolean loggedCase(Scanner sc){
        int optionSelected;
        boolean didNotQuit = true;
        Book book;
        System.out.print(loggedOptions);
        optionSelected = Integer.valueOf(sc.nextLine());
        switch(optionSelected){
            case 1:
                listAllBooks();
                break;
            case 2:
                book = enterLibraryBookInformation(sc);
                modifyAvailabilityOfBook(book, false, checkOutOkMsg, checkOutErrorMsg);
                break;
            case 3:
                book = enterLibraryBookInformation(sc);
                modifyAvailabilityOfBook(book, true, returnOkMsg, returnErrorMsg);
                break;
            case 7:
                didNotQuit = false;
                break;
            default:
                System.out.println();
                System.out.println("Select a valid option!");
                System.out.println();
        }
        return didNotQuit;
    }

    private boolean notLoggedCase(Scanner sc) {
        int optionSelected;
        boolean didNotQuit = true;
        System.out.print(notLoggedOptions);
        optionSelected = Integer.valueOf(sc.nextLine());
        switch(optionSelected){
            case 1:
                listAllBooks();
                break;
            case 2:
                System.out.println("not implemented");
                break;
            case 3:
                System.out.println("not implemented");
                break;
            case 4:
                System.out.println("not implemented");
                break;
            case 5:
                didNotQuit = false;
                break;
            default:
                System.out.println();
                System.out.println("Select a valid option!");
                System.out.println();
        }
        return didNotQuit;
    }


    private void modifyAvailabilityOfBook(Book book, boolean availability, String successMsg, String errorMsg) {
        System.out.println();
        // TODO: Cambiar el usuario
        if (bookManager.changeAvailability(book, availability,"111-1111")){
            System.out.println(successMsg);
        } else {
            System.out.println(errorMsg);
        }
    }


    private Book enterLibraryBookInformation(Scanner sc){
        System.out.println();
        System.out.print("Please enter the book title: ");
        String title = sc.nextLine();
        System.out.print("Please enter the book author/s: ");
        String author = sc.nextLine();
        System.out.print("Please enter the book year of publication: ");
        int yearPublished = Integer.valueOf(sc.nextLine());
        return new Book(title, author, yearPublished);
    }

    private void listAllBooks(){
        System.out.println();
        System.out.println("Available books");
        System.out.printf(format,"Title","Author","Year Published");
        bookManager.getAvailableBookDetails(format);
    }
}
