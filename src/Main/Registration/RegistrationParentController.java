import java.sql.*;

public class RegistrationParentController {
    Connection conn=null;
    Statement stmt=null;

    String connectionURL=
            "jdbc:sqlserver://localhost:1433;"
                    +"database=test_2;"
                    +"user=anson;"
                    +"password=password;";


        try {

        conn = DriverManager.getConnection(connectionURL);

        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql = "SELECT stu_id, stu_fname, stu_lname FROM student";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            int stu_id = rs.getInt("stu_id");
            String stu_fname= rs.getString("stu_fname");
            String stu_lname= rs.getString("stu_lname");

            System.out.print("\nStu_ID:" + stu_id);
            System.out.print("\nStu_FName:" + stu_fname);
            System.out.print("\nStu_LName:" + stu_lname);

        }
        rs.close();

    }
        catch (
    SQLException e){

        System.out.println("unable to make connection with db");
        e.printStackTrace();
    }
}
