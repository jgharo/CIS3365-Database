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

public class StudentController implements Initializable {

    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student,String> col_stu_id;
    @FXML
    private TableColumn<Student, String> col_par_id;
    @FXML
    private TableColumn<Student, String> col_class_id;
    @FXML
    private TableColumn<Student, String> col_course_id;
    @FXML
    private TableColumn<Student, String> col_stu_fname;
    @FXML
    private TableColumn<Student, String> col_stu_lname;
    @FXML
    private TableColumn<Student, String> col_stu_dob;
    @FXML
    private TableColumn<Student, String> col_stu_email;
    @FXML
    private TableColumn<Student, String> col_stu_phone;
    @FXML
    private TableColumn<Student, String> col_stu_address;
    @FXML
    private TableColumn<Student, String> col_stu_lang;
    @FXML
    private TableColumn<Student, String> col_stu_abs;
    @FXML
    private TableColumn<Student, String> col_date_mod;

    @FXML
    private TextField stu_id;
    @FXML
    private TextField par_id;
    @FXML
    private TextField class_id;
    @FXML
    private TextField course_id;
    @FXML
    private TextField stu_fname;
    @FXML
    private TextField stu_lname;
    @FXML
    private TextField stu_dob;
    @FXML
    private TextField stu_email;
    @FXML
    private TextField stu_phone;
    @FXML
    private TextField stu_address;
    @FXML
    private TextField stu_lang;
    @FXML
    private TextField stu_abs;

    ObservableList<Student> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu= con.prepareCall("{CALL student_select()}");

            ResultSet rs= stmtstu.executeQuery();
            while (rs.next()){
                oblist.add(new Student(rs.getString("Stu_ID"),rs.getString("Par_ID"),rs.getString("Class_ID"),rs.getString("Course_ID"),
                        rs.getString("Stu_FName"), rs.getString("Stu_LName"), rs.getString("Stu_DOB"),rs.getString("Stu_Email"),rs.getString("Stu_Phone"),
                        rs.getString("Stu_Address"), rs.getString("Stu_Lang"), rs.getString("Stu_Abs"), rs.getString("Date_Mod")));



            }

        } catch (SQLException ex){
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void add_Student(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_insert(?,?,?,?,?,?,?,?)}");

            stmtstu.setString(1, this.stu_fname.getText());
            stmtstu.setString(2, this.stu_lname.getText());
            stmtstu.setString(3, this.stu_dob.getText());
            stmtstu.setString(4, this.stu_email.getText());
            stmtstu.setString(5, this.stu_phone.getText());
            stmtstu.setString(6, this.stu_address.getText());
            stmtstu.setString(7, this.stu_lang.getText());
            stmtstu.setString(8, this.stu_abs.getText());

            stmtstu.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_Student(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_delete(?)}");

            stmtstu.setString(1, this.stu_id.getText());

            stmtstu.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_Student(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_update(?,?,?,?,?,?,?,?,?,?,?,?)}");

            stmtstu.setString(1, this.stu_id.getText());
            stmtstu.setString(2, this.par_id.getText());
            stmtstu.setString(3, this.class_id.getText());
            stmtstu.setString(4, this.course_id.getText());
            stmtstu.setString(5, this.stu_fname.getText());
            stmtstu.setString(6, this.stu_lname.getText());
            stmtstu.setString(7, this.stu_dob.getText());
            stmtstu.setString(8, this.stu_email.getText());
            stmtstu.setString(9, this.stu_phone.getText());
            stmtstu.setString(10, this.stu_address.getText());
            stmtstu.setString(11, this.stu_lang.getText());
            stmtstu.setString(12, this.stu_abs.getText());


            stmtstu.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
