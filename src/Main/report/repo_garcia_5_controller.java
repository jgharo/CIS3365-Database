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

public class repo_garcia_5_controller implements Initializable {

    @FXML
    private TableView<repo_garcia_5> table;
    @FXML
    private TableColumn<repo_garcia_5, String> col_stu_id;
    @FXML
    private TableColumn<repo_garcia_5, String> col_stu_fname;
    @FXML
    private TableColumn<repo_garcia_5, String> col_stu_lname;
    @FXML
    private TableColumn<repo_garcia_5, String> col_stu_phone;
    @FXML
    private TableColumn<repo_garcia_5, String> col_fath_fname;
    @FXML
    private TableColumn<repo_garcia_5, String> col_fath_lname;
    @FXML
    private TableColumn<repo_garcia_5, String> col_fath_phone;
    @FXML
    private TableColumn<repo_garcia_5, String> col_fath_email;
    @FXML
    private TableColumn<repo_garcia_5, String> col_moth_fname;
    @FXML
    private TableColumn<repo_garcia_5, String> col_moth_lname;
    @FXML
    private TableColumn<repo_garcia_5, String> col_moth_phone;
    @FXML
    private TableColumn<repo_garcia_5, String> col_teach_fname;
    @FXML
    private TableColumn<repo_garcia_5, String> col_teach_lname;
    @FXML
    private TableColumn<repo_garcia_5, String> col_class_name;
    @FXML
    private TableColumn<repo_garcia_5, String> col_stu_abs;

    ObservableList<repo_garcia_5> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_garcia_5()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_garcia_5(rs.getString("Stu_ID"), rs.getString("Stu_FName"), rs.getString("Stu_LName"), rs.getString("Stu_Phone"),
                        rs.getString("Fath_FName"), rs.getString("Fath_LName"), rs.getString("Fath_Phone"),
                        rs.getString("Moth_FName"), rs.getString("Moth_LName"), rs.getString("Moth_Phone"), rs.getString("Teach_FName"), rs.getString("Teach_LName"),
                        rs.getString("Class_Name"),rs.getString("Stu_Abs")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_garcia_5.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_stu_fname.setCellValueFactory(new PropertyValueFactory<>("Stu_FName"));
        col_stu_lname.setCellValueFactory(new PropertyValueFactory<>("Stu_LName"));
        col_stu_phone.setCellValueFactory(new PropertyValueFactory<>("Stu_Phone"));
        col_fath_fname.setCellValueFactory(new PropertyValueFactory<>("Fath_FName"));
        col_fath_lname.setCellValueFactory(new PropertyValueFactory<>("Fath_LName"));
        col_fath_phone.setCellValueFactory(new PropertyValueFactory<>("Fath_Phone"));
        col_moth_fname.setCellValueFactory(new PropertyValueFactory<>("Moth_FName"));
        col_moth_lname.setCellValueFactory(new PropertyValueFactory<>("Moth_LName"));
        col_moth_phone.setCellValueFactory(new PropertyValueFactory<>("Moth_Phone"));
        col_moth_fname.setCellValueFactory(new PropertyValueFactory<>("Moth_FName"));
        col_moth_lname.setCellValueFactory(new PropertyValueFactory<>("Moth_LName"));
        col_teach_fname.setCellValueFactory(new PropertyValueFactory<>("Teach_FName"));
        col_teach_lname.setCellValueFactory(new PropertyValueFactory<>("Teach_LName"));
        col_class_name.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
        col_stu_abs.setCellValueFactory(new PropertyValueFactory<>("Stu_Abs"));


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
