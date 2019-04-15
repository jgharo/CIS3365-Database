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

public class StudentRegistrationFormController implements Initializable {

    @FXML
    private TableView<StudentRegistrationForm> table;
    @FXML
    private TableColumn<StudentRegistrationForm,String> col_sreg_id;
    @FXML
    private TableColumn<StudentRegistrationForm, String> col_stu_id;
    @FXML
    private TableColumn<StudentRegistrationForm, String> col_sreg_date;

    @FXML
    private TextField sreg_id;
    @FXML
    private TextField stu_id;
    @FXML
    private TextField sreg_date;


    ObservableList<StudentRegistrationForm> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL student_reg_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new StudentRegistrationForm(rs.getString("SReg_ID"),rs.getString("Stu_ID"), rs.getString("SReg_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(StudentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_sreg_id.setCellValueFactory(new PropertyValueFactory<>("SReg_ID"));
        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_sreg_date.setCellValueFactory(new PropertyValueFactory<>("SReg_Date"));

        table.setItems(oblist);

    }

    @FXML
    private void add_StudentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL student_reg_insert(?,?)}");

            stmt.setString(1, this.stu_id.getText());
            stmt.setString(2, this.sreg_date.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(StudentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_StudentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_reg_delete(?)}");

            stmtstu.setString(1, this.sreg_id.getText());

            stmtstu.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_StudentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL student_reg_update(?,?,?)}");

            stmt.setString(1, this.sreg_id.getText());
            stmt.setString(2, this.stu_id.getText());
            stmt.setString(3, this.sreg_date.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
