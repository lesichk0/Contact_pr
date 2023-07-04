package org.example;

import org.example.DbConfig.ConnectionSettings;
import org.example.DbConfig.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRepo implements IContactRepo {
    //connection to db
    private final Connection connection = ConnectionSettings.getConnection();
    public ContactRepo() throws SQLException {}

    //add new contact to db
    public void AddNewContact(String NameOfOwner, String phoneNumber, String email, boolean isImportant, String details) throws SQLException {
        String query = "INSERT INTO contact (" + DbConfig.name_of_owner + ", " + DbConfig.phone_numb + ", " + DbConfig.email +
                ", " + DbConfig.is_important +
                ", " + DbConfig.details +") VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, NameOfOwner);
        ps.setString(2, phoneNumber);
        ps.setString(3, email);
        ps.setBoolean(4, isImportant);
        ps.setString(5, details);
        ps.executeUpdate();
        System.out.println("Successfully added!\n");
    }

    //select all contacts
    public void SelectAllContacts() throws SQLException {
        String query = "SELECT * FROM contact";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        PrintAll(rs);
    }

    //shows only important contacts
    public void SelectOnlyImportant() throws SQLException {
        String query = "select * from contact where " + DbConfig.is_important + " = 1";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        PrintAll(rs);
    }

    //shows sorted contacts by name of owner
    public void SelectSortedContacts() throws SQLException {
        String query = "select * from contact order by " + DbConfig.name_of_owner;
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        PrintAll(rs);
    }

    //delete contact by name of owner
    public void Delete(String nameOfOwner) throws SQLException {
        if(IsInDb(nameOfOwner)){
            String query = "DELETE FROM contact WHERE " + DbConfig.name_of_owner + " = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nameOfOwner);
            ps.executeUpdate();
            System.out.println("Successfully deleted!\n");
        }
        else System.out.println("There is no contact with given name!\n");
    }

    //delete contact from important by name of owner
    public void DeleteFromImportant(String nameOfOwner) throws SQLException {
        if(IsInDb(nameOfOwner)){
            String query = "UPDATE contact SET isImportant = 0 where " + DbConfig.name_of_owner + " = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nameOfOwner);
            ps.executeUpdate();
            System.out.println("Successfully deleted!\n");
        }else System.out.println("There is no contact with given name!\n");
    }

    //add contact to important by name of owner
    public void AddToImportant(String nameOfOwner) throws SQLException {
        if(IsInDb(nameOfOwner)){
            String query = "UPDATE contact SET isImportant = 1 where " + DbConfig.name_of_owner + " = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nameOfOwner);
            ps.executeUpdate();
            System.out.println("Successfully added!\n");
        }else System.out.println("There is no contact with given name!\n");
    }

    //search and print contact by owner's name
    public void SearchContact(String ownName) throws SQLException {
        if(IsInDb(ownName)){
            String query = "select * from contact where " + DbConfig.name_of_owner + " = '" + ownName +"'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            PrintAll(rs);
        }else System.out.println("There is no contact with given name!\n");
    }

    //print all contacts in result set
    public void PrintAll(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("\uD83D\uDCCC " + rs.getInt(DbConfig.contact_id) + ")");
            System.out.println("Name of owner: " + rs.getString(DbConfig.name_of_owner));
            System.out.println("\uD83D\uDCDE Phone number: " + rs.getString(DbConfig.phone_numb));
            System.out.println("✉ Email: " + rs.getString(DbConfig.email));
            if (rs.getBoolean(DbConfig.is_important)) {
                System.out.println("This contact is important!⭐");
            }
            System.out.println("\uD83D\uDDD2 Details: " + rs.getString(DbConfig.details) + "\n");
        }
    }

    //check is there record in db with given owner name
    public boolean IsInDb(String ownerName) throws SQLException {
        String query = "select * from contact where " + DbConfig.name_of_owner + " = '" + ownerName +"'";
        PreparedStatement ps = connection.prepareStatement(query);
        //ps.setString(1, ownerName);
        ResultSet rs = ps.executeQuery(query);
        return rs.next();
    }
}