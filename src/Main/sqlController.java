import Information.DBconnect;
import Information.ParentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sqlController {
    public TextArea sqlText;
    public Button executeButton;
    public TextArea resultText;
    public Button menuButton;
    String value;

    public void executeSql(ActionEvent actionEvent) {
        StringBuilder sql = new StringBuilder(10000);
        try {
            Connection con = DBconnect.getConnection();
            Statement statement = con.createStatement();
            String sqlString = sqlText.getText();
            ResultSet resultSet = statement.executeQuery(sqlString);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int colNum = resultSetMetaData.getColumnCount();

            resultText.clear();

            while (resultSet.next()) {
                for (int i = 0; i <= colNum; i++) {
                    if (i > 0) value = resultSet.getString(i);
                    sql.append(value = " ");
                    System.out.print(value + " ");
                    value = "";
                }
                sql.append("\n");
                System.out.println("\n");
            }
            resultText.setText(sql.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void menu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tableSelection.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Table Selection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
