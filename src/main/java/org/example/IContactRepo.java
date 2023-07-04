package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IContactRepo {
    public void AddNewContact(String NameOfOwner, String phoneNumber, String email, boolean isImportant, String details) throws SQLException;
    public void SelectAllContacts() throws SQLException;
    public void SelectOnlyImportant() throws SQLException;
    public void SelectSortedContacts() throws SQLException;
    public void Delete(String nameOfOwner) throws SQLException;
    public void DeleteFromImportant(String nameOfOwner) throws SQLException;
    public void AddToImportant(String nameOfOwner) throws SQLException;
    public void SearchContact(String ownName) throws SQLException;
    public void PrintAll(ResultSet rs) throws SQLException;
    public boolean IsInDb(String ownerName) throws SQLException;
}
