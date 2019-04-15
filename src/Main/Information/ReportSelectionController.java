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

public class ReportSelectionController implements Initializable
{
    @FXML public Button backButton;
    @FXML public Button submitButton;
    @FXML ComboBox<String> reportSelectionBox;

    private String[] reports = new String[]{"PLACEHOLDER"};

    //private String[] fxml = new String[]{

    public void initialize(URL url, ResourceBundle rb)
    {
        reportSelectionBox.getItems().addAll(reports);
    }

    public void submitButtonPushed(ActionEvent event) throws IOException
    {
        for (String report : reports)
        {
            if (reportSelectionBox.getValue().contentEquals(report))
            {
                System.out.println(report);
                String reportLink = report + ".fxml";
                System.out.println(reportLink);
                String reportName = report + " Report";

                Parent root = FXMLLoader.load(getClass().getResource(reportLink));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle(reportName);
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }
    }

    public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("tableSelection.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Table Selection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}