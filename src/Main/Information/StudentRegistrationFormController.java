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

public class StudentRegistrationFormController implements Initializable {

    @FXML
    private TableView<StudentRegistrationForm> table;
    @FXML
    private TableColumn<StudentRegistrationForm, String> col_sreg_id;
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


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<StudentRegistrationForm> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            StudentRegistrationForm stu = newval;
            if (stu != null) {
                sreg_id.setText(stu.getSReg_ID());
                stu_id.setText(stu.getStu_ID());
                sreg_date.setText(stu.getSReg_Date());


            }
        });
        table.getSelectionModel().clearSelection();

        setStudentRegTable();
        displayDatabase();

    }

    private void setStudentRegTable() {
        col_sreg_id.setCellValueFactory(new PropertyValueFactory<>("SReg_ID"));
        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_sreg_date.setCellValueFactory(new PropertyValueFactory<>("SReg_Date"));
    }


    private void displayDatabase(){

        ObservableList<StudentRegistrationForm> studentregInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL student_reg_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                studentregInfo.add(new StudentRegistrationForm(rs.getString("SReg_ID"),rs.getString("Stu_ID"), rs.getString("SReg_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(StudentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(studentregInfo);

    }

    @FXML
    private void add_StudentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL student_reg_insert(?,?)}");

            stmt.setString(1, this.stu_id.getText());
            stmt.setString(2, this.sreg_date.getText());
            stmt.execute();
            setStudentRegTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(StudentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            sreg_id.setText("");
            stu_id.setText("");
            sreg_date.setText("");
        }

    }

    @FXML
    private void delete_StudentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_reg_delete(?)}");

            stmtstu.setString(1, this.sreg_id.getText());

            stmtstu.execute();
            setStudentRegTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            sreg_id.setText("");
            stu_id.setText("");
            sreg_date.setText("");
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
            setStudentRegTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            sreg_id.setText("");
            stu_id.setText("");
            sreg_date.setText("");
        }

    }

    @FXML
    private void clear_StudentRegistrationForm(ActionEvent actionEvent) {
        sreg_id.setText("");
        stu_id.setText("");
        sreg_date.setText("");
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
