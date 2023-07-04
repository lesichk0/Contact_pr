package org.example.Models;

public class Contact {
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private boolean isImportant;
    public boolean getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(boolean isImportant) {
        this.isImportant = isImportant;
    }

    private String details;
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Contact(String name, String phoneNumber, boolean isImportant, String details){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isImportant = isImportant;
        this.details = details;
    }

    public Contact() {}
}
