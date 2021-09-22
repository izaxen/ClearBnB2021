package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DbConnect {

    INSTANCE;
    Connection sqlCon;

    private DbConnect(){

        try {
            sqlCon = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/clearbnb","root","password");
            System.out.println("Connected to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
