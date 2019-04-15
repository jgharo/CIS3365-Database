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

public class AdminVerificationController implements Initializable {

    @FXML
    private TableView<AdminVerification> table;
    @FXML
    private TableColumn<AdminVerification,String> col_madm_id;
    @FXML
    private TableColumn<AdminVerification, String> col_adm_id;
    @FXML
    private TableColumn<AdminVerification, String> col_adm_verify;

    @FXML
    private TextField madm_id;
    @FXML
    private TextField adm_id;
    @FXML
    private TextField adm_verify;


    ObservableList<AdminVerification> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL admin_verification_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new AdminVerification(rs.getString("MAdm_ID"),rs.getString("Adm_ID"), rs.getString("Adm_Verify")));

            }

        } catch (SQLException ex){
            Logger.getLogger(AdminVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_madm_id.setCellValueFactory(new PropertyValueFactory<>("MAdm_ID"));
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_adm_verify.setCellValueFactory(new PropertyValueFactory<>("Adm_Verify"));

        table.setItems(oblist);

    }

    @FXML
    private void add_AdminVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_verification_insert(?,?,?)}");

            stmt.setString(1, this.madm_id.getText());
            stmt.setString(2, this.adm_id.getText());
            stmt.setString(3, this.adm_verify.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_AdminVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL admin_verification_delete(?)}");

            stmtstu.setString(1, this.adm_id.getText());

            stmtstu.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(AdminVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_AdminVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL admin_verification_update(?,?,?)}");

            stmt.setString(1, this.madm_id.getText());
            stmt.setString(2, this.adm_id.getText());
            stmt.setString(3, this.adm_verify.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(AdminVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
