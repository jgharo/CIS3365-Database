
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login Screen.fxml"));
        primaryStage.setTitle("St. Philip of Jesus Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    /*public static void main(String[] args) throws SQLException {
        launch(args);

        Connection con=null;
        String url=
                "jdbc:sqlserver://localhost:1433;"
                        +"database=test_2;"
                        +"user=admin;"
                        +"password=password;";

        try {
            con = DriverManager.getConnection(url);
            System.out.println("you are connected");
            System.out.println(con);
        }
        catch (SQLException e){
            System.out.println("unable to make connection with db");
            e.printStackTrace();
        }


    }*/


}
