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

public class repo_mcallister_5_controller implements Initializable {

    @FXML
    private TableView<repo_mcallister_5> table;
    @FXML
    private TableColumn<repo_mcallister_5, String> col_stu_id;
    @FXML
    private TableColumn<repo_mcallister_5, String> col_stu_fname;
    @FXML
    private TableColumn<repo_mcallister_5, String> col_stu_lname;
    @FXML
    private TableColumn<repo_mcallister_5, String> col_stu_verify;
    @FXML
    private TableColumn<repo_mcallister_5, String> col_adm_id;
    @FXML
    private TableColumn<repo_mcallister_5, String> col_adm_fname;
    @FXML
    private TableColumn<repo_mcallister_5, String> col_adm_lname;


    ObservableList<repo_mcallister_5> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_mcallister_5()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_mcallister_5(rs.getString("Stu_ID"), rs.getString("Stu_FName"), rs.getString("Stu_LName"), rs.getString("Stu_Verify"),
                        rs.getString("Adm_ID"),rs.getString("Adm_FName"),rs.getString("Adm_LName")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_mcallister_5.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_stu_fname.setCellValueFactory(new PropertyValueFactory<>("Stu_FName"));
        col_stu_lname.setCellValueFactory(new PropertyValueFactory<>("Stu_LName"));
        col_stu_verify.setCellValueFactory(new PropertyValueFactory<>("Stu_Verify"));
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_adm_fname.setCellValueFactory(new PropertyValueFactory<>("Adm_FName"));
        col_adm_lname.setCellValueFactory(new PropertyValueFactory<>("Adm_LName"));



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
