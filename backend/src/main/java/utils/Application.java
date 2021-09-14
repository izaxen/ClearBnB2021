package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    Connection con;

    public Application() {
        this.connectToDB();
        if (con == null) {
            System.out.println("Connection not working");
        }
        else{
            new Routes();
        }
    }

    private void connectToDB() {
        {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/clearbnb","root","password");
                System.out.println("Connected to DB");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
