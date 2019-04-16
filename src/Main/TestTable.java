
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestTable extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Information/Sacraments.fxml"));
        primaryStage.setTitle("St. Philip of Jesus Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
