import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController
{
    @FXML public TextField usernameField;
    @FXML public PasswordField passwordField;
    @FXML public Label LoginStatus;
    @FXML public Button LoginButton;
    @FXML public Button registerButton;

    public void LoginButtonPushed(ActionEvent event) throws IOException
    {
        if (usernameField.getText().equals("Admin") && passwordField.getText().equals("Password"))
        {
            LoginStatus.setText("Login Success");
            Parent root = FXMLLoader.load(getClass().getResource("Information/tableSelection.fxml"));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Table Selection");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        else
        {
            LoginStatus.setVisible(true);
            LoginStatus.setText("Login Failed");
        }
    }
}
