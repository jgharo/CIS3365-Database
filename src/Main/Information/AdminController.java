package Information;
import Information.DBconnect;
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

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController implements Initializable {

    @FXML
    public TableView <Admin> adminTable;
    public TableColumn <Admin, String> idCol;
    public TableColumn <Admin, String> firstCol;
    public TableColumn <Admin, String> lastCol;
    public TableColumn <Admin, String>phoneCol;
    public TableColumn <Admin, String> emailCol;
    public TableColumn <Admin, String> addressColl;
    public TableColumn <Admin, String> dateCol;

    @FXML
    public TextField id;
    public TextField firstText;
    public TextField lastText;
    public TextField phoneText;
    public TextField emailText;
    public TextField addressText;

    @FXML
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button menuButton;

    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Admin> observableList = FXCollections.observableArrayList();
        adminTable.setItems(observableList);
        adminTable.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            Admin admin = newval;
            if (admin != null) {
                id.setText(admin.getAdm_ID());
                firstText.setText(admin.getAdm_FName());
                lastText.setText(admin.getAdm_LName());
                phoneText.setText(admin.getAdm_Phone());
                emailText.setText(admin.getAdm_Email());
                addressText.setText(admin.getAdm_Address());
                adminTable.refresh();
            }
        });
        adminTable.getSelectionModel().clearSelection();

        setAdminTable();
        displayDatabase();

    }

    private void setAdminTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        firstCol.setCellValueFactory(new PropertyValueFactory<>("Adm_FName"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("Adm_LName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("Adm_Phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Adm_Address"));
        addressColl.setCellValueFactory(new PropertyValueFactory<>("Adm_Email"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date_Mod"));
    }

    private void displayDatabase() {
        ObservableList<Admin> adminInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_Select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                adminInfo.add(new Admin(rs.getString("adm_ID"), rs.getString("adm_FName"), rs.getString("adm_LName"),
                        rs.getString("adm_Phone"), rs.getString("adm_Email"), rs.getString("adm_Address"), rs.getString("date_Mod")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        }
        adminTable.setItems(adminInfo);

    }

    public void addAdmin(ActionEvent actionEvent) throws SQLException {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_insert(?,?,?,?,?)}");

                stmtAdmin.setString(1, firstText.getText());
                stmtAdmin.setString(2, lastText.getText());
                stmtAdmin.setString(3, phoneText.getText());
                stmtAdmin.setString(4, addressText.getText());
                stmtAdmin.setString(5, emailText.getText());

                stmtAdmin.execute();
                setAdminTable();
                displayDatabase();
                adminTable.getSelectionModel().clearSelection();
                adminTable.refresh();
                con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            firstText.setText("");
            lastText.setText("");
            phoneText.setText("");
            emailText.setText("");
            addressText.setText("");
        }
    }

    public void updateAdmin(ActionEvent actionEvent) {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_update(?,?,?,?,?,?)}");

            stmtAdmin.setString(1, firstText.getText());
            stmtAdmin.setString(2, lastText.getText());
            stmtAdmin.setString(3, emailText.getText());
            stmtAdmin.setString(4, addressText.getText());
            stmtAdmin.setString(5, phoneText.getText());
            stmtAdmin.setString(6, id.getText());

            stmtAdmin.executeUpdate();
            setAdminTable();
            displayDatabase();
            adminTable.getSelectionModel().clearSelection();
            adminTable.refresh();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            id.setText("");
            firstText.setText("");
            lastText.setText("");
            phoneText.setText("");
            emailText.setText("");
            addressText.setText("");
        }
    }


    public void deleteAdmin(ActionEvent actionEvent) {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_delete(?)}");

            stmtAdmin.setString(1, id.getText());

            stmtAdmin.execute();
            setAdminTable();
            displayDatabase();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            firstText.setText("");
            lastText.setText("");
            phoneText.setText("");
            emailText.setText("");
            addressText.setText("");
            id.setText("");
        }
    }

    public void menu(ActionEvent actionEvent) {
        var stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(returnScene);
    }
}
