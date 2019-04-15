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


    ObservableList<AdminRegistrationForm> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL admin_reg_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new AdminRegistrationForm(rs.getString("AReg_ID"),rs.getString("Adm_ID"), rs.getString("AReg_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(AdminRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_areg_id.setCellValueFactory(new PropertyValueFactory<>("AReg_ID"));
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_areg_date.setCellValueFactory(new PropertyValueFactory<>("AReg_Date"));

        table.setItems(oblist);

    }

    @FXML
    private void add_AdminRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_reg_insert(?,?)}");

            stmt.setString(1, this.adm_id.getText());
            stmt.setString(2, this.areg_date.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_AdminRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_reg_delete(?)}");

            stmt.setString(1, this.areg_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(AdminRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_AdminRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_reg_update(?,?,?)}");

            stmt.setString(1, this.areg_id.getText());
            stmt.setString(2, this.adm_id.getText());
            stmt.setString(3, this.areg_date.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(AdminRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
