package Information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class UserManageController {
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
    
    public Button deleteButton;
    public Button modifyButton;
    public Button addButton;
    public Button menuButton;
    Scene returnScene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        @FXML
        private TableView<display_student_model> table;
        @FXML
        private TableColumn<display_student_model,String> col_stu_id;
        @FXML
        private TableColumn<display_student_model, String> col_par_id;
        @FXML
        private TableColumn<display_student_model, String> col_class_id;
        @FXML
        private TableColumn<display_student_model, String> col_course_id;
        @FXML
        private TableColumn<display_student_model, String> col_stu_fname;
        @FXML
        private TableColumn<display_student_model, String> col_stu_lname;
        @FXML
        private TableColumn<display_student_model, String> col_stu_dob;
        @FXML
        private TableColumn<display_student_model, String> col_stu_email;
        @FXML
        private TableColumn<display_student_model, String> col_stu_phone;
        @FXML
        private TableColumn<display_student_model, String> col_stu_address;
        @FXML
        private TableColumn<display_student_model, String> col_stu_lang;
        @FXML
        private TableColumn<display_student_model, String> col_stu_abs;
        @FXML
        private TableColumn<display_student_model, String> col_date_mod;

        ObservableList<display_student_model> oblist = FXCollections.observableArrayList();

        @Override
        public void initialize(URL location, ResourceBundle resources){

            try {

                Connection con = DBconnection.getConnection();

                CallableStatement stmtstu= con.prepareCall("{CALL student_select()}");

                ResultSet rs= stmtstu.executeQuery();
                while (rs.next()){
                    oblist.add(new display_student_model(rs.getString("Stu_ID"),rs.getString("Par_ID"),rs.getString("Class_ID"),rs.getString("Course_ID"),
                            rs.getString("Stu_FName"), rs.getString("Stu_LName"), rs.getString("Stu_DOB"),rs.getString("Stu_Email"),rs.getString("Stu_Phone"),
                            rs.getString("Stu_Address"), rs.getString("Stu_Lang"), rs.getString("Stu_Abs"), rs.getString("Date_Mod")));



                }

            } catch (SQLException ex){
                Logger.getLogger(display_student_controller.class.getName()).log(Level.SEVERE, null, ex);
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

            table.setItems(oblist);

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
