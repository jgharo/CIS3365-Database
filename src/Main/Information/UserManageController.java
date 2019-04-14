package Information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManageController implements Initializable {
    @FXML
    public TableView studentTable;
    public TableColumn col_stu_id;
    public TableColumn col_par_id;
    public TableColumn col_class_id;
    public TableColumn col_course_id;
    public TableColumn col_stu_fname;
    public TableColumn col_stu_lname;
    public TableColumn col_stu_dob;
    public TableColumn col_stu_email;
    public TableColumn col_stu_phone;
    public TableColumn col_stu_address;
    public TableColumn col_stu_lang;
    public TableColumn col_stu_abs;
    public TableColumn col_date_mod;
    @FXML
    public Button deleteButton;
    public Button modifyButton;
    public Button addButton;
    public Button menuButton;
    Scene returnScene;


    ObservableList<informationStudent> students = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn=null;
        Statement stmt=null;
        String connectionURL=
                "jdbc:sqlserver://localhost:1433;"
                        +"database=test_2;"
                        +"user=anson;"
                        +"password=password;";
        ObservableList<informationStudent> studentInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtStu = con.prepareCall("{CALL student_Select()}");

            ResultSet rs = stmtStu.executeQuery();
            while (rs.next()){
                studentInfo.add(new informationStudent(rs.getString("Stu_ID"),rs.getString("Par_ID"),rs.getString("Class_ID"),rs.getString("Course_ID"),
                        rs.getString("Stu_FName"), rs.getString("Stu_LName"), rs.getString("Stu_DOB"),rs.getString("Stu_Email"),rs.getString("Stu_Phone"),
                        rs.getString("Stu_Address"), rs.getString("Stu_Lang"), rs.getString("Stu_Abs"), rs.getString("Date_Mod")));


            }

        } catch (SQLException ex) {
            Logger.getLogger( InformationAdmin.class.getName()).log(Level.SEVERE, null, ex);

        }

        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_par_id.setCellValueFactory(new PropertyValueFactory<>("Par_ID"));
        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_course_id.setCellValueFactory(new PropertyValueFactory<>("Course_ID"));
        col_stu_fname.setCellValueFactory(new PropertyValueFactory<>("Stu_FName"));
        col_stu_lname.setCellValueFactory(new PropertyValueFactory<>("Stu_LName"));
        col_stu_dob.setCellValueFactory(new PropertyValueFactory<>("Stu_DOB"));
        col_stu_email.setCellValueFactory(new PropertyValueFactory<>("Stu_Email"));
        col_stu_phone.setCellValueFactory(new PropertyValueFactory<>("Stu_Phone"));
        col_stu_address.setCellValueFactory(new PropertyValueFactory<>("Stu_Address"));
        col_stu_lang.setCellValueFactory(new PropertyValueFactory<>("Stu_Lang"));
        col_stu_abs.setCellValueFactory(new PropertyValueFactory<>("Stu_Abs"));
        col_date_mod.setCellValueFactory(new PropertyValueFactory<>("Date_Mod"));

        studentTable.setItems(students);


    }



    public void deleteStudent(ActionEvent actionEvent) {
    }

    public void modifyStudent(ActionEvent actionEvent) {
    }

    public void addStudent(ActionEvent actionEvent) {
    }

    public void returnMenu(ActionEvent actionEvent) {
        var stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();


        stage.setScene(returnScene);
    }
}
