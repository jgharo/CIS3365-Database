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

public class repo_deluna_3_controller implements Initializable {

    @FXML
    private TableView<repo_deluna_3> table;
    @FXML
    private TableColumn<repo_deluna_3, String> col_stu_id;
    @FXML
    private TableColumn<repo_deluna_3, String> col_stu_fname;
    @FXML
    private TableColumn<repo_deluna_3, String> col_stu_lname;
    @FXML
    private TableColumn<repo_deluna_3, String> col_class_name;
    @FXML
    private TableColumn<repo_deluna_3, String> col_class_room;
    @FXML
    private TableColumn<repo_deluna_3, String> col_class_lang;

    ObservableList<repo_deluna_3> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_deluna_3()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_deluna_3(rs.getString("Stu_ID"),rs.getString("Stu_FName"), rs.getString("Stu_LName"), rs.getString("Class_Name"),
                        rs.getString("Class_Room"),rs.getString("Class_Lang")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_deluna_3.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_stu_fname.setCellValueFactory(new PropertyValueFactory<>("Stu_FName"));
        col_stu_lname.setCellValueFactory(new PropertyValueFactory<>("Stu_LName"));
        col_class_room.setCellValueFactory(new PropertyValueFactory<>("Class_Room"));
        col_class_lang.setCellValueFactory(new PropertyValueFactory<>("Class_Lang"));



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
