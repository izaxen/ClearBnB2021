package utils;

import java.sql.*;

public class Application {
    Connection con;

    public Application() {

        new Routes();
        doExampleQuery();
        }


    private void doExampleQuery() { //TODO erase this one

        try {
            int userID = 1;
            // Good habit: Never use non-prepared statement
            // Statement stmt = con.createStatement();
            // SQL-injection possible with the query below since we're concatenating strings
            // ResultSet rs=stmt.executeQuery("SELECT * FROM emp WHERE emp.id = " + userID);
            PreparedStatement pStatement = DbConnect.INSTANCE.sqlCon.prepareStatement ("SELECT ID, first_name, date_created FROM user WHERE id = ?");
            pStatement.setInt(1, userID);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                System.out.println(
                        rs.getInt(1)
                                + "  " +
                                rs.getString(2)
                                + "  " +
                                rs.getTimestamp(3)
                );
            }
            DbConnect.INSTANCE.sqlCon.close();
        }catch(Exception e){

            System.out.println(e);
        }
    }
}
