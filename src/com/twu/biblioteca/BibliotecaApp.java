package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;


public class BibliotecaApp {

    private static boolean isLogged;
    private static String loggedUserID;
    private static LibraryManager libraryManager;
    private static UserManager userManager;
    private static UI ui;

    public static void main(String[] args) {
        ui = new UI();
        libraryManager = new LibraryManager();
        userManager = new UserManager();
        isLogged = false;
        ui.welcomeMessagePrinter();
        showInformationBasedOnUserOption();
    }

    private static void showInformationBasedOnUserOption(){
        String optionSelected;
        do{
            optionSelected = ui.getOption(isLogged);
            switch(optionSelected){
                case "1":
                    ui.listAllBooks(libraryManager);
                    break;
                case "2":
                    if(isLogged)
                        modifyAvailabilityOfBook(false);
                    else
                        System.out.println("Please log in first");
                    break;
                case "3":
                    if(isLogged)
                        modifyAvailabilityOfBook(true);
                    else
                        System.out.println("Please log in first");
                    break;
                case "6":
                    loggedUserID = getLoggedUserID();
                    setLoggedState(loggedUserID);
                case "q":
                    break;
                default:
                    System.out.println();
                    System.out.println("Select a valid option!");
                    System.out.println();
            }
        } while(ui.isNotQuitting(optionSelected));
    }

    private static String getLoggedUserID() {
        if (isLogged)
            return "";
        else
            return ui.getLogInCredentials(userManager);
    }

    private static void setLoggedState(String userIdLogged) {
        if(userIdLogged.isEmpty()){
            isLogged = false;
            ui.showNotLogged();
        }
        else {
            isLogged = true;
            ui.showLogged();
        }
    }

    private static void modifyAvailabilityOfBook(boolean availability) {
        Book book = ui.enterLibraryBookInformation();
        if (libraryManager.changeAvailability(book, availability,loggedUserID)){
            ui.printSuccess(availability);
        } else {
            ui.printError(availability);
        }
    }
}
