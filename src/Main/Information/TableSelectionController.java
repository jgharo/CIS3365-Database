package Information;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableSelectionController implements Initializable
{
    @FXML public Button backButton;
    @FXML public Button ReportButton;
    @FXML public Button submitButton;
    @FXML ComboBox<String> tableSelectionBox;

    private String[] tables = new String[]{"Master Admins", "Master Admin Account", "Admin",
            "Admin Account", "Admin Registration", "Attendance Check", "Attendance Record", "Classes",
            "Class Language", "Classroom", "Courses", "Parents",
            "Parents Registration", "Parents Transactions", "Sacraments", "Students", "Student Registration",
            "Student Transactions", "Teacher Accounts", "Teacher Enrollment", "Teacher Registration",
            "Parents Transaction History", "Student Transaction History", "Student Verification",
            "Teacher Verification", "Admin Verification"};

    public void initialize(URL url, ResourceBundle rb)
    {
        tableSelectionBox.getItems().addAll(tables);
    }

    public void submitButtonPushed(ActionEvent event) throws IOException
    {
        for (String table : tables)
        {
            if (tableSelectionBox.getValue().contentEquals(table))
            {
                System.out.println(table);
                String tableLink = table + ".fxml";
                System.out.println(tableLink);
                String tableName = table + " Table";
                System.out.println(tableName);

                Parent root = FXMLLoader.load(getClass().getResource(tableLink));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle(tableName);
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }
    }

    public void reportButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../report/reportSelection.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Report Selection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Login Screen.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}