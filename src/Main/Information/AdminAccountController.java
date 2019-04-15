package Information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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


    ObservableList<AdminAccount> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL admin_account_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new AdminAccount(rs.getString("Adm_Acc_ID"),rs.getString("Adm_ID"), rs.getString("Adm_User"), rs.getString("Adm_Pass")));

            }

        } catch (SQLException ex){
            Logger.getLogger(AdminRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_adm_acc_id.setCellValueFactory(new PropertyValueFactory<>("Adm_Acc_ID"));
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_adm_user.setCellValueFactory(new PropertyValueFactory<>("Adm_User"));
        col_adm_pass.setCellValueFactory(new PropertyValueFactory<>("Adm_Pass"));

        table.setItems(oblist);

    }

    @FXML
    private void add_AdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_account_insert(?,?,?)}");

            stmt.setString(1, this.adm_id.getText());
            stmt.setString(2, this.adm_user.getText());
            stmt.setString(3, this.adm_pass.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_AdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_account_delete(?)}");

            stmt.setString(1, this.adm_acc_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_AdminAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_account_update(?,?,?,?)}");

            stmt.setString(1, this.adm_acc_id.getText());
            stmt.setString(2, this.adm_id.getText());
            stmt.setString(3, this.adm_user.getText());
            stmt.setString(4, this.adm_pass.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
