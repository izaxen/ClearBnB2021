package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.User;
import models.UserRepostitory;

import java.sql.*;
import java.util.Optional;

public class Application {

    public Application() {

        new Routes();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearbNb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserRepostitory userRepostitory = new UserRepostitory(entityManager);

        User user = new User("Mackan","Udd","bla@bla.com",1000,"lösen");
        Optional<User> savedUser = userRepostitory.save(user);

        userRepostitory.findAll();


        //doExampleQuery();
        //doExampleQuery1();
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

        }catch(Exception e){

            System.out.println(e);
        }
    }
    private void doExampleQuery1() { //TODO erase this one

        try {
            System.out.println(DbConnect.INSTANCE.sqlCon.isClosed());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            int userID = 1;
            // Good habit: Never use non-prepared statement
            // Statement stmt = con.createStatement();
            // SQL-injection possible with the query below since we're concatenating strings
            // ResultSet rs=stmt.executeQuery("SELECT * FROM emp WHERE emp.id = " + userID);
            PreparedStatement pStatement = DbConnect.INSTANCE.sqlCon.prepareStatement ("SELECT ID, first_name, sur_name, date_created FROM user WHERE id = ?");
            pStatement.setInt(1, userID);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                System.out.println(
                        rs.getInt(1)
                                + "  " +
                                rs.getString(2)
                                + "  " +
                                rs.getString(3)
                                + "  " +
                                rs.getTimestamp(4)
                );
            }

        }catch(Exception e){

            System.out.println(e);
        }
    }
}
