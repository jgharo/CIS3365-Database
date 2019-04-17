
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login Screen.fxml"));
        primaryStage.setTitle("St. Philip of Jesus Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
