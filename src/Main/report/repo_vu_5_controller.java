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

public class repo_vu_5_controller implements Initializable {

    @FXML
    private TableView<repo_vu_5> table;
    @FXML
    private TableColumn<repo_vu_5, String> col_course_id;
    @FXML
    private TableColumn<repo_vu_5, String> col_course_name;
    @FXML
    private TableColumn<repo_vu_5, String> col_class_name;
    @FXML
    private TableColumn<repo_vu_5, String> col_teach_fname;
    @FXML
    private TableColumn<repo_vu_5, String> col_teach_lname;



    ObservableList<repo_vu_5> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_vu_5()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_vu_5(rs.getString("Course_ID"), rs.getString("Course_Name"), rs.getString("Class_Name"), rs.getString("Teach_FName"),
                        rs.getString("Teach_LName")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_vu_5.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_course_id.setCellValueFactory(new PropertyValueFactory<>("Course_ID"));
        col_course_name.setCellValueFactory(new PropertyValueFactory<>("Course_Name"));
        col_class_name.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
        col_teach_fname.setCellValueFactory(new PropertyValueFactory<>("Teach_FName"));
        col_teach_lname.setCellValueFactory(new PropertyValueFactory<>("Teach_LName"));





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
