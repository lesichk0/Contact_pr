package Repositories;

import Repositories.Contracts.IGenericRepo;
import org.example.DbConfig.ConnectionSettings;
import org.example.DbConfig.DbConfig;
import Repositories.Contracts.IContactRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRepo implements IContactRepo {
    IGenericRepo iGenericRepo = new GenericRepo();
    //connection to db
    private final Connection connection = ConnectionSettings.getConnection();
    public ContactRepo() throws SQLException {}

    //add new contact to db
    @Override
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
    @Override
    public void SelectAllContacts() throws SQLException {
        String query = "SELECT * FROM contact";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        iGenericRepo.printAll(rs);
    }

    //shows only important contacts
    @Override
    public void SelectOnlyImportant() throws SQLException {
        String query = "select * from contact where " + DbConfig.is_important + " = 1";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        iGenericRepo.printAll(rs);
    }

    //shows sorted contacts by name of owner
    @Override
    public void SelectSortedContacts() throws SQLException {
        String query = "select * from contact order by " + DbConfig.name_of_owner;
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        iGenericRepo.printAll(rs);
    }

    //delete contact by name of owner
    @Override
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
    @Override
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
    @Override
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
    @Override
    public void SearchContact(String ownName) throws SQLException {
        if(IsInDb(ownName)){
            String query = "select * from contact where " + DbConfig.name_of_owner + " = '" + ownName +"'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            iGenericRepo.printAll(rs);
        }else System.out.println("There is no contact with given name!\n");
    }

    //check is there record in db with given owner name
    @Override
    public boolean IsInDb(String ownerName) throws SQLException {
        String query = "select * from contact where " + DbConfig.name_of_owner + " = '" + ownerName +"'";
        PreparedStatement ps = connection.prepareStatement(query);
        //ps.setString(1, ownerName);
        ResultSet rs = ps.executeQuery(query);
        return rs.next();
    }
}