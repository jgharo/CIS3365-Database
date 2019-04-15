package report;
import Information.DBconnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class repo_martin_2_controller implements Initializable {

    @FXML
    private TableView<repo_martin_2> table;
    @FXML
    private TableColumn<repo_martin_2, String> col_class_id;
    @FXML
    private TableColumn<repo_martin_2, String> col_teach_id;
    @FXML
    private TableColumn<repo_martin_2, String> col_class_name;
    @FXML
    private TableColumn<repo_martin_2, String> col_class_lang;
    @FXML
    private TableColumn<repo_martin_2, String> col_class_room;


    ObservableList<repo_martin_2> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_martin_2()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_martin_2(rs.getString("Class_ID"),rs.getString("Teach_ID"), rs.getString("Class_Name"), rs.getString("Class_Lang"),
                        rs.getString("Class_Room")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_martin_2.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_class_name.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
        col_class_lang.setCellValueFactory(new PropertyValueFactory<>("Class_Lang"));
        col_class_room.setCellValueFactory(new PropertyValueFactory<>("Class_Room"));



        table.setItems(oblist);


    }

    @SuppressWarnings("Duplicates")
    public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("reportSelection.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Report Selection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
