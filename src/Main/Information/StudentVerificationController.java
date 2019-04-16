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


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<StudentVerification> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            StudentVerification stu = newval;
            if (stu != null) {
                adm_id.setText(stu.getAdm_ID());
                stu_id.setText(stu.getStu_ID());
                stu_verify.setText(stu.getStu_Verify());


            }
        });
        table.getSelectionModel().clearSelection();

        setStudentVerTable();
        displayDatabase();

    }

    private void setStudentVerTable() {
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_stu_verify.setCellValueFactory(new PropertyValueFactory<>("Stu_Verify"));
    }


    private void displayDatabase(){

        ObservableList<StudentVerification> studentverInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL student_verification_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                studentverInfo.add(new StudentVerification(rs.getString("Adm_ID"),rs.getString("Stu_ID"), rs.getString("Stu_Verify")));

            }

        } catch (SQLException ex){
            Logger.getLogger(StudentVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(studentverInfo);

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
            setStudentVerTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(StudentVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            adm_id.setText("");
            stu_id.setText("");
            stu_verify.setText("");
        }

    }

    @FXML
    private void delete_StudentVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_verification_delete(?)}");

            stmtstu.setString(1, this.stu_id.getText());

            stmtstu.execute();
            setStudentVerTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            adm_id.setText("");
            stu_id.setText("");
            stu_verify.setText("");
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
            setStudentVerTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            adm_id.setText("");
            stu_id.setText("");
            stu_verify.setText("");
        }

    }

    @FXML
    private void clear_StudentVerification(ActionEvent actionEvent) {
        adm_id.setText("");
        stu_id.setText("");
        stu_verify.setText("");
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
