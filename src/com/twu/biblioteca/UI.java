package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Credential;

import java.util.Scanner;

class UI {
    private String welcomeMessage = "*** WELCOME TO THE TWU LIBRARY ***";
    private String commonOptions = "\n1) List all books\n2) Check out book\n3) Return book\n4) List all movies\n5) Check out movie\n6) ";
    private String format = "%-25s  %-25s  %-5s \n";
    private String returnOkMsg = "Thank you for returning the book";
    private String returnErrorMsg = "That is not a valid book to return.";
    private String checkOutOkMsg = "Thank you! Enjoy the book";
    private String checkOutErrorMsg = "That book is not available.";
    private boolean isLogged;
    private LibraryManager libraryManager;
    private UserManager userManager;

    UI() {

        libraryManager = new LibraryManager();
        userManager = new UserManager();
        isLogged = false;
    }

    void welcomeMessagePrinter(){
        System.out.println(welcomeMessage);
    }

    void getOption(){
        Scanner sc = new Scanner(System.in);
        String optionSelected;
        do{
            ShowOptionsIfLoggedOrNot();
            optionSelected = sc.nextLine();
            switch(optionSelected){
                case "1":
                    listAllBooks();
                    break;
                case "2":
                    if(isLogged)
                        modifyAvailabilityOfBook(false, checkOutOkMsg, checkOutErrorMsg);
                    else
                        System.out.println("Please log in first");
                    break;
                case "3":
                    if(isLogged)
                        modifyAvailabilityOfBook(true, returnOkMsg, returnErrorMsg);
                    else
                        System.out.println("Please log in first");
                    break;
                case "6":
                    isLogged = logIn(sc);
                case "q":
                    break;
                default:
                    System.out.println();
                    System.out.println("Select a valid option!");
                    System.out.println();
            }
        } while(isNotQuitting(optionSelected));
    }

    private boolean logIn(Scanner sc){
        System.out.println();
        System.out.print("Please enter your library ID: ");
        String libraryID = sc.nextLine();
        System.out.print("Please enter your password: ");
        char[] password = sc.nextLine().toCharArray();
        return userManager.loginUser(new Credential(libraryID, password));
    }

    private boolean isNotQuitting(String optionSelected) {
        return !optionSelected.toLowerCase().equals("q");
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

    private void ShowOptionsIfLoggedOrNot(){
        if(isLogged){
            System.out.print(commonOptions + "Log out\nPlease enter an option or enter Q to quit: ");
        }
        else {
            System.out.print(commonOptions + "Log In\nPlease enter an option or enter Q to quit: ");
        }
    }

    private void listAllBooks(){
        System.out.println();
        System.out.println("Available books");
        System.out.printf(format,"Title","Author","Year Published");
        libraryManager.getAvailableBookDetails(format);
    }

    private void modifyAvailabilityOfBook(boolean availability, String successMsg, String errorMsg) {
        System.out.println();
        Book book = enterLibraryBookInformation(new Scanner(System.in));
        // TODO: Cambiar el usuario
        if (libraryManager.changeAvailability(book, availability,"111-1111")){
            System.out.println(successMsg);
        } else {
            System.out.println(errorMsg);
        }
    }

}
