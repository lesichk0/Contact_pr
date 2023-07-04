package org.example.DbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSettings {
    private static final String url = "jdbc:mysql://localhost:3306/contacts";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "12345";
    private static Connection con;

    //connect to db
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, username, password);
        }catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        return con;
    }

    //close connection to db
    public static void disconnect() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}