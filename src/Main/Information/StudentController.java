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

    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Student> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            Student stu = newval;
            if (stu != null) {
                stu_id.setText(stu.getStu_ID());
                par_id.setText(stu.getPar_ID());
                class_id.setText(stu.getClass_ID());
                course_id.setText(stu.getCourse_ID());
                stu_fname.setText(stu.getStu_FName());
                stu_lname.setText(stu.getStu_LName());
                stu_dob.setText(stu.getStu_DOB());
                stu_email.setText(stu.getStu_Email());
                stu_phone.setText(stu.getStu_Phone());
                stu_address.setText(stu.getStu_Address());
                stu_lang.setText(stu.getStu_Lang());
                stu_abs.setText(stu.getStu_Abs());
                table.refresh();
            }
        });
        table.getSelectionModel().clearSelection();

        setStudentTable();
        displayDatabase();

    }

    private void setStudentTable() {
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
    }


    private void displayDatabase() {

        ObservableList<Student> studentInfo = FXCollections.observableArrayList();
        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_select()}");

            ResultSet rs = stmtstu.executeQuery();
            while (rs.next()) {
                studentInfo.add(new Student(rs.getString("Stu_ID"), rs.getString("Par_ID"), rs.getString("Class_ID"), rs.getString("Course_ID"),
                        rs.getString("Stu_FName"), rs.getString("Stu_LName"), rs.getString("Stu_DOB"), rs.getString("Stu_Email"), rs.getString("Stu_Phone"),
                        rs.getString("Stu_Address"), rs.getString("Stu_Lang"), rs.getString("Stu_Abs"), rs.getString("Date_Mod")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(studentInfo);
    }

    @FXML
    private void add_Student(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_insert(?,?,?,?,?,?,?,?,?,?,?)}");

            stmtstu.setString(1, this.par_id.getText());
            stmtstu.setString(2, this.class_id.getText());
            stmtstu.setString(3, this.course_id.getText());
            stmtstu.setString(4, this.stu_fname.getText());
            stmtstu.setString(5, this.stu_lname.getText());
            stmtstu.setString(6, this.stu_dob.getText());
            stmtstu.setString(7, this.stu_email.getText());
            stmtstu.setString(8, this.stu_phone.getText());
            stmtstu.setString(9, this.stu_address.getText());
            stmtstu.setString(10, this.stu_lang.getText());
            stmtstu.setString(11, this.stu_abs.getText());

            stmtstu.execute();
            setStudentTable();
            displayDatabase();;
            table.getSelectionModel().clearSelection();
            table.refresh();;
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{

            stu_id.setText("");
            par_id.setText("");
            class_id.setText("");
            course_id.setText("");
            stu_fname.setText("");
            stu_lname.setText("");
            stu_dob.setText("");
            stu_email.setText("");
            stu_phone.setText("");
            stu_address.setText("");
            stu_lang.setText("");
            stu_abs.setText("");

        }

    }

    @FXML
    private void delete_Student(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_delete(?)}");

            stmtstu.setString(1, this.stu_id.getText());


            stmtstu.execute();
            setStudentTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            stu_id.setText("");
            par_id.setText("");
            class_id.setText("");
            course_id.setText("");
            stu_fname.setText("");
            stu_lname.setText("");
            stu_dob.setText("");
            stu_email.setText("");
            stu_phone.setText("");
            stu_address.setText("");
            stu_lang.setText("");
            stu_abs.setText("");
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
            setStudentTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            finally{
            stu_id.setText("");
            par_id.setText("");
            class_id.setText("");
            course_id.setText("");
            stu_fname.setText("");
            stu_lname.setText("");
            stu_dob.setText("");
            stu_email.setText("");
            stu_phone.setText("");
            stu_address.setText("");
            stu_lang.setText("");
            stu_abs.setText("");
        }


    }

    @FXML
    private void clear_Student(ActionEvent actionEvent){
        stu_id.setText("");
        par_id.setText("");
        class_id.setText("");
        course_id.setText("");
        stu_fname.setText("");
        stu_lname.setText("");
        stu_dob.setText("");
        stu_email.setText("");
        stu_phone.setText("");
        stu_address.setText("");
        stu_lang.setText("");
        stu_abs.setText("");

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
