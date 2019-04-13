package Registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Statement;

public class RegistrationStudentController {
    Connection con = null;
    String url = "jdbc:sqlserver://localhost:1433;"
            + "database=test_2;"
            + "user=anson;"
            + "password=password;";

    try {
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        CallableStatement stmt= con.prepareCall("{CALL Register_Student(?,?,?,?,?,?,?,?,?)}");

        stmt.setString(1,"FatherName");
        stmt.setString(2,"FathLName");
        stmt.setString(3,"8/8/1990");
        stmt.setString(4,"email@email.com");
        stmt.setString(5,"333-333-3333");
        stmt.setString(6,"1414 address street");
        stmt.setString(7,"ENG");
        stmt.setString(8,"MothName");
        stmt.setString(9,"MothLName");


        stmt.executeUpdate();
        System.out.println("Added new student");
        stmt.close();

    }
        catch (SQLException e){
        System.out.println("unable to make connection with db");
        e.printStackTrace();
    }
}
}