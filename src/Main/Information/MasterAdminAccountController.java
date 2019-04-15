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


    ObservableList<MasterAdminAccount> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL master_admin_account_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new MasterAdminAccount(rs.getString("Madm_Acc_ID"),rs.getString("Madm_ID"), rs.getString("Madm_User"), rs.getString("Madm_Pass")));

            }

        } catch (SQLException ex){
            Logger.getLogger(MasterAdminAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_madm_acc_id.setCellValueFactory(new PropertyValueFactory<>("Madm_Acc_ID"));
        col_madm_id.setCellValueFactory(new PropertyValueFactory<>("Madm_ID"));
        col_madm_user.setCellValueFactory(new PropertyValueFactory<>("Madm_User"));
        col_madm_pass.setCellValueFactory(new PropertyValueFactory<>("Madm_Pass"));

        table.setItems(oblist);

    }

    @FXML
    private void add_MasterAdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL master_admin_account_insert(?,?,?)}");

            stmt.setString(1, this.madm_id.getText());
            stmt.setString(2, this.madm_user.getText());
            stmt.setString(3, this.madm_pass.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(MasterAdminAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_MasterAdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL master_admin_account_delete(?)}");

            stmt.setString(1, this.madm_acc_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(MasterAdminAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_MasterAdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL master_admin_account_update(?,?,?,?)}");

            stmt.setString(1, this.madm_acc_id.getText());
            stmt.setString(2, this.madm_id.getText());
            stmt.setString(3, this.madm_user.getText());
            stmt.setString(4, this.madm_pass.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(MasterAdminAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
