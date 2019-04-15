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

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminAccountController implements Initializable {

    @FXML
    private TableView<AdminAccount> table;
    @FXML
    private TableColumn<AdminAccount,String> col_adm_acc_id;
    @FXML
    private TableColumn<AdminAccount, String> col_adm_id;
    @FXML
    private TableColumn<AdminAccount, String> col_adm_user;
    @FXML
    private TableColumn<AdminAccount, String> col_adm_pass;

    @FXML
    private TextField adm_acc_id;
    @FXML
    private TextField adm_id;
    @FXML
    private TextField adm_user;
    @FXML
    private TextField adm_pass;

    @FXML
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button menuButton;

    Scene returnScene;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ObservableList<AdminAccount> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            AdminAccount admin = newval;
            if (admin != null) {
                adm_acc_id.setText(admin.getAdm_Acc_ID());
                adm_id.setText(admin.getAdm_ID());
                adm_user.setText(admin.getAdm_User());
                adm_pass.setText(admin.getAdm_Pass());
            }
        });
        table.getSelectionModel().clearSelection();

        setAdminAccountTable();
        displayDatabase();
    }

    private void setAdminAccountTable() {
        col_adm_acc_id.setCellValueFactory(new PropertyValueFactory<>("Adm_Acc_ID"));
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_adm_user.setCellValueFactory(new PropertyValueFactory<>("Adm_User"));
        col_adm_pass.setCellValueFactory(new PropertyValueFactory<>("Adm_Pass"));
    }

    private void displayDatabase() {
        ObservableList<AdminAccount> adminInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_account_select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                adminInfo.add(new AdminAccount(rs.getString("adm_Acc_ID"), rs.getString("adm_ID"), rs.getString("adm_User"),
                        rs.getString("adm_Pass")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(adminInfo);
    }

    @FXML
    private void add_AdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_account_insert(?,?,?)}");

            stmt.setString(1, adm_id.getText());
            stmt.setString(2, adm_user.getText());
            stmt.setString(3, adm_pass.getText());

            stmt.execute();
            setAdminAccountTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        } catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            adm_id.setText("");
            adm_user.setText("");
            adm_pass.setText("");
        }
    }

    @FXML
    private void delete_AdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_account_delete(?)}");

            stmt.setString(1, adm_acc_id.getText());

            stmt.execute();
            setAdminAccountTable();
            displayDatabase();
            con.close();
        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            adm_acc_id.setText("");
        }

    }

    @FXML
    private void update_AdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_account_update(?,?,?,?)}");

            stmt.setString(1, adm_acc_id.getText());
            stmt.setString(2, adm_id.getText());
            stmt.setString(3, adm_user.getText());
            stmt.setString(4, adm_pass.getText());

            stmt.executeUpdate();
            setAdminAccountTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            adm_acc_id.setText("");
            adm_id.setText("");
            adm_user.setText("");
            adm_pass.setText("");
        }

    }

    public void clearAdminAccount (ActionEvent actionEvent) {
        adm_acc_id.setText("");
        adm_id.setText("");
        adm_user.setText("");
        adm_pass.setText("");
    }

    public void mainmenu(ActionEvent actionEvent) {
        var stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(returnScene);
    }
}
