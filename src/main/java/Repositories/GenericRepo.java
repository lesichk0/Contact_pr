package Repositories;

import Repositories.Contracts.IGenericRepo;
import org.example.DbConfig.DbConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericRepo implements IGenericRepo {
    //print all contacts in result set
    public void printAll(ResultSet rs) throws SQLException {
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
}
