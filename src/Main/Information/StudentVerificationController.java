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

public class StudentVerificationController implements Initializable {

    @FXML
    private TableView<StudentVerification> table;
    @FXML
    private TableColumn<StudentVerification,String> col_adm_id;
    @FXML
    private TableColumn<StudentVerification, String> col_stu_id;
    @FXML
    private TableColumn<StudentVerification, String> col_stu_verify;

    @FXML
    private TextField adm_id;
    @FXML
    private TextField stu_id;
    @FXML
    private TextField stu_verify;


    ObservableList<StudentVerification> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL student_verification_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new StudentVerification(rs.getString("Adm_ID"),rs.getString("Stu_ID"), rs.getString("Stu_Verify")));

            }

        } catch (SQLException ex){
            Logger.getLogger(StudentVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_stu_verify.setCellValueFactory(new PropertyValueFactory<>("Stu_Verify"));

        table.setItems(oblist);

    }

    @FXML
    private void add_StudentVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL student_verification_insert(?,?,?)}");

            stmt.setString(1, this.adm_id.getText());
            stmt.setString(2, this.stu_id.getText());
            stmt.setString(3, this.stu_verify.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(StudentVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_StudentVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_verification_delete(?)}");

            stmtstu.setString(1, this.stu_id.getText());

            stmtstu.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_StudentVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL student_verification_update(?,?,?)}");

            stmt.setString(1, this.adm_id.getText());
            stmt.setString(2, this.stu_id.getText());
            stmt.setString(3, this.stu_verify.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
