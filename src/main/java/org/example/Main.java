package org.example;

import Repositories.ContactRepo;
import Repositories.Contracts.IContactRepo;
import org.example.DbConfig.ConnectionSettings;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        IContactRepo iContactRepo = new ContactRepo();
        Scanner scan = new Scanner(System.in);
        String ToDo ;
        while(true){
            System.out.println("Select an option or type \"help\" to see all commands");
            ToDo = scan.nextLine();
            switch (ToDo.toLowerCase()) {
                case "help" -> {
                    System.out.println("\n\"add\" - add new contact");
                    System.out.println("\"show all\" - shows all contacts");
                    System.out.println("\"show sorted\" - shows all contacts sorted by name of owner");
                    System.out.println("\"show important\" - shows all important contacts");
                    System.out.println("\"search\" - check is there contact with given name");
                    System.out.println("\"del\" - delete contact by name of owner");
                    System.out.println("\"del from important\" - delete contact from important list");
                    System.out.println("\"add to important\" - add contact to important list");
                    System.out.println("\"close\" - to kill the program");
                }
                case "add" -> {
                    System.out.println("Enter contact's owner name: ");
                    String ownerName = scan.nextLine();
                    System.out.println("Enter phone number: ");
                    String numb = scan.nextLine();
                    System.out.println("Enter email: ");
                    String email = scan.nextLine();
                    System.out.println("Enter details: ");
                    String details = scan.nextLine();
                    System.out.println("Is important \n 1 - yes\n 2 - no");
                    String isImportant = scan.nextLine();
                    if (Objects.equals(isImportant, "1")) {
                        iContactRepo.AddNewContact(ownerName, numb, email, true, details);
                    } else if(Objects.equals(isImportant, "2")) {
                        iContactRepo.AddNewContact(ownerName, numb, email, false, details);
                    } else{
                        System.out.println("There is no such option!\n");
                    }
                }
                case "show all" -> iContactRepo.SelectAllContacts();
                case "show sorted" -> iContactRepo.SelectSortedContacts();
                case "show important" -> iContactRepo.SelectOnlyImportant();
                case "search" -> {
                    System.out.println("\nEnter owner name: ");
                    String ownName = scan.nextLine();
                    iContactRepo.SearchContact(ownName);
                }
                case "del" -> {
                    System.out.println("\nEnter owner name: ");
                    String ownName = scan.nextLine();
                    iContactRepo.Delete(ownName);
                }
                case "del from important" -> {
                    System.out.println("\nEnter owner name: ");
                    String owName = scan.nextLine();
                    iContactRepo.DeleteFromImportant(owName);
                }
                case "add to important" -> {
                    System.out.println("\nEnter owner name: ");
                    String owName = scan.nextLine();
                    iContactRepo.AddToImportant(owName);
                }
                case "close" -> {
                    ConnectionSettings.disconnect();
                    return;
                }
                default -> System.out.println("\nThere is no option like this!\n");
            }
        }
    }
}