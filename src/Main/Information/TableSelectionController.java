package Information;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TableSelectionController implements Initializable
{
    @FXML
    ChoiceBox<String> tableSelectionBox;

    public void initialize(URL url, ResourceBundle rb)
    {
        tableSelectionBox.setValue("Please Select a Table...");

        tableSelectionBox.getItems().add("Admin");
        tableSelectionBox.getItems().add("Admin Accounts");
    }
}