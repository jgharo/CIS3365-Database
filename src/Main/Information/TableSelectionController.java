package Information;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableSelectionController implements Initializable
{
    @FXML ComboBox<String> tableSelectionBox;

    public void initialize(URL url, ResourceBundle rb)
    {
        tableSelectionBox.getItems().addAll( "Master Admins", "Master Admin Account", "Admin",
                "Admin Account", "Admin Registration", "Attendance Check", "Attendance Record", "Classes",
                "Class Language", "Classroom", "Courses", "Master Admins", "Master Admin Account", "Parents",
                "informationParent Registration", "informationParent Transactions", "Sacraments", "Students", "informationStudent Registration",
                "informationStudent Transactions", "Teacher Accounts", "Teacher Enrollment", "Teacher Registration",
                "informationParent Transaction History", "informationStudent Transaction History", "informationStudent Verification",
                "Teacher Verification", "Admin Verification");
    }

    public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login Screen.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}