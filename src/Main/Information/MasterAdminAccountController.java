package Information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class MasterAdminAccountController implements Initializable {

    @FXML
    private TableView<MasterAdminAccount> table;
    @FXML
    private TableColumn<MasterAdminAccount,String> col_madm_acc_id;
    @FXML
    private TableColumn<MasterAdminAccount, String> col_madm_id;
    @FXML
    private TableColumn<MasterAdminAccount, String> col_madm_user;
    @FXML
    private TableColumn<MasterAdminAccount, String> col_madm_pass;

    @FXML
    private TextField madm_acc_id;
    @FXML
    private TextField madm_id;
    @FXML
    private TextField madm_user;
    @FXML
    private TextField madm_pass;

    Scene returnScene;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ObservableList<MasterAdminAccount> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            MasterAdminAccount admin = newval;
            if (admin != null) {
                madm_acc_id.setText(admin.getMAdm_Acc_ID());
                madm_id.setText(admin.getMAdm_ID());
                madm_user.setText(admin.getMAdm_User());
                madm_pass.setText(admin.getMAdm_Pass());
            }
        });
        table.getSelectionModel().clearSelection();

        setMasterAdminAccountTable();
        displayDatabase();
    }

    private void setMasterAdminAccountTable() {
        col_madm_acc_id.setCellValueFactory(new PropertyValueFactory<>("MAdm_Acc_ID"));
        col_madm_id.setCellValueFactory(new PropertyValueFactory<>("MAdm_ID"));
        col_madm_user.setCellValueFactory(new PropertyValueFactory<>("MAdm_User"));
        col_madm_pass.setCellValueFactory(new PropertyValueFactory<>("MAdm_Pass"));
    }

    private void displayDatabase() {
        ObservableList<MasterAdminAccount> madminInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL master_admin_account_select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                madminInfo.add(new MasterAdminAccount(rs.getString("MAdm_Acc_ID"), rs.getString("MAdm_ID"), rs.getString("MAdm_User"),
                        rs.getString("MAdm_Pass")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MasterAdmin.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(madminInfo);
    }

    @FXML
    private void add_MasterAdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL master_admin_account_insert(?,?,?)}");

            stmt.setString(1, madm_id.getText());
            stmt.setString(2, madm_user.getText());
            stmt.setString(3, madm_pass.getText());

            stmt.execute();
            setMasterAdminAccountTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        } catch (SQLException ex){
            Logger.getLogger(MasterAdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            madm_id.setText("");
            madm_user.setText("");
            madm_pass.setText("");
        }
    }

    @FXML
    private void delete_MasterAdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL master_admin_account_delete(?)}");

            stmt.setString(1, madm_acc_id.getText());

            stmt.execute();
            setMasterAdminAccountTable();
            displayDatabase();
            con.close();
        }
        catch (SQLException ex){
            Logger.getLogger(MasterAdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            madm_acc_id.setText("");
        }

    }

    @FXML
    private void update_MasterAdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL master_admin_account_update(?,?,?,?)}");

            stmt.setString(1, madm_acc_id.getText());
            stmt.setString(2, madm_id.getText());
            stmt.setString(3, madm_user.getText());
            stmt.setString(4, madm_pass.getText());

            stmt.executeUpdate();
            setMasterAdminAccountTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(MasterAdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            madm_acc_id.setText("");
            madm_id.setText("");
            madm_user.setText("");
            madm_pass.setText("");
        }

    }

    public void clearMasterAdminAccount (ActionEvent actionEvent) {
        madm_acc_id.setText("");
        madm_id.setText("");
        madm_user.setText("");
        madm_pass.setText("");
    }

    @SuppressWarnings("Duplicates")
    public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("tableSelection.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Table Selection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
