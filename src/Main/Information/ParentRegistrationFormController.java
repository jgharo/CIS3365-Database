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

public class ParentRegistrationFormController implements Initializable {

    @FXML
    private TableView<ParentRegistrationForm> table;
    @FXML
    private TableColumn<ParentRegistrationForm,String> col_preg_id;
    @FXML
    private TableColumn<ParentRegistrationForm, String> col_par_id;
    @FXML
    private TableColumn<ParentRegistrationForm, String> col_preg_date;

    @FXML
    private TextField preg_id;
    @FXML
    private TextField par_id;
    @FXML
    private TextField preg_date;


    ObservableList<ParentRegistrationForm> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL parent_reg_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new ParentRegistrationForm(rs.getString("PReg_ID"),rs.getString("Par_ID"), rs.getString("PReg_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(ParentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_preg_id.setCellValueFactory(new PropertyValueFactory<>("PReg_ID"));
        col_par_id.setCellValueFactory(new PropertyValueFactory<>("Par_ID"));
        col_preg_date.setCellValueFactory(new PropertyValueFactory<>("PReg_Date"));

        table.setItems(oblist);

    }

    @FXML
    private void add_ParentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL parent_reg_insert(?,?)}");

            stmt.setString(1, this.par_id.getText());
            stmt.setString(2, this.preg_date.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(ParentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_ParentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL parent_reg_delete(?)}");

            stmt.setString(1, this.preg_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_ParentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL parent_reg_update(?,?,?)}");

            stmt.setString(1, this.preg_id.getText());
            stmt.setString(2, this.par_id.getText());
            stmt.setString(3, this.preg_date.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
