package Information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminRegistrationFormController implements Initializable {

    @FXML
    private TableView<AdminRegistrationForm> table;
    @FXML
    private TableColumn<AdminRegistrationForm,String> col_areg_id;
    @FXML
    private TableColumn<AdminRegistrationForm, String> col_adm_id;
    @FXML
    private TableColumn<AdminRegistrationForm, String> col_areg_date;

    @FXML
    private TextField areg_id;
    @FXML
    private TextField adm_id;
    @FXML
    private TextField areg_date;

    @FXML
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button menuButton;

    Scene returnScene;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<AdminRegistrationForm> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            AdminRegistrationForm admin = newval;
            if (admin != null) {
                areg_id.setText(admin.getAReg_ID());
                adm_id.setText(admin.getAdm_ID());
                areg_date.setText(admin.getAReg_Date());}
        });
        table.getSelectionModel().clearSelection();

        setAdminRegistrationFormTable();
        displayDatabase();
    }

    private void setAdminRegistrationFormTable() {
        col_areg_id.setCellValueFactory(new PropertyValueFactory<>("AReg_ID"));
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_areg_date.setCellValueFactory(new PropertyValueFactory<>("AReg_Date"));
    }

    private void displayDatabase() {
        ObservableList<AdminRegistrationForm> adminInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_reg_select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                adminInfo.add(new AdminRegistrationForm(rs.getString("AReg_ID"), rs.getString("adm_ID"), rs.getString("AReg_Date")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(adminInfo);
    }

    public void add_AdminRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_reg_insert(?,?)}");

            stmt.setString(1, adm_id.getText());
            stmt.setString(2, areg_date.getText());

            stmt.execute();
            setAdminRegistrationFormTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            adm_id.setText("");
            areg_date.setText("");
        }

    }

    public void delete_AdminRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_reg_delete(?)}");

            stmt.setString(1, areg_id.getText());

            stmt.execute();
            setAdminRegistrationFormTable();
            displayDatabase();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            areg_id.setText("");
        }

    }

    public void update_AdminRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_reg_update(?,?,?)}");

            stmt.setString(1, areg_id.getText());
            stmt.setString(2, adm_id.getText());
            stmt.setString(3, areg_date.getText());

            stmt.executeUpdate();
            setAdminRegistrationFormTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            areg_id.setText("");
            adm_id.setText("");
            areg_date.setText("");
        }

    }

    public void mainmenu(ActionEvent actionEvent) {
        var stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(returnScene);

    }

    public void clear_AdminRegistrationForm(ActionEvent actionEvent) {
        areg_id.setText("");
        adm_id.setText("");
        areg_date.setText("");
    }

}
