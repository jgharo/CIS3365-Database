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

public class TeacherEnrollmentController implements Initializable {

    @FXML
    private TableView<TeacherEnrollment> table;
    @FXML
    private TableColumn<TeacherEnrollment, String> col_teach_id;
    @FXML
    private TableColumn<TeacherEnrollment, String> col_course_id;
    @FXML
    private TableColumn<TeacherEnrollment, String> col_teach_enroll;

    @FXML
    private TextField teach_id;
    @FXML
    private TextField course_id;
    @FXML
    private TextField teach_enroll;


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TeacherEnrollment> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            TeacherEnrollment stu = newval;
            if (stu != null) {
                teach_id.setText(stu.getTeach_ID());
                course_id.setText(stu.getCourse_ID());
                teach_enroll.setText(stu.getTeach_Enroll());


            }
        });
        table.getSelectionModel().clearSelection();

        setTeacherEnrTable();
        displayDatabase();

    }

    private void setTeacherEnrTable() {
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_course_id.setCellValueFactory(new PropertyValueFactory<>("Course_ID"));
        col_teach_enroll.setCellValueFactory(new PropertyValueFactory<>("Teach_Enroll"));
    }


    private void displayDatabase(){

        ObservableList<TeacherEnrollment> teachenrInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL teacher_enrollment_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                teachenrInfo.add(new TeacherEnrollment(rs.getString("Teach_ID"),rs.getString("Course_ID"), rs.getString("Teach_Enroll")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TeacherEnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(teachenrInfo);

    }

    @FXML
    private void add_TeacherEnrollment(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_enrollment_insert(?,?,?)}");

            stmt.setString(1, this.teach_id.getText());
            stmt.setString(2, this.course_id.getText());
            stmt.setString(3, this.teach_enroll.getText());
            stmt.execute();
            setTeacherEnrTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TeacherEnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            teach_id.setText("");
            course_id.setText("");
            teach_enroll.setText("");
        }

    }

    @FXML
    private void delete_TeacherEnrollment(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL teacher_enrollment_delete(?)}");

            stmtstu.setString(1, this.teach_id.getText());

            stmtstu.execute();
            setTeacherEnrTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherEnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            teach_id.setText("");
           course_id.setText("");
            teach_enroll.setText("");
        }

    }

    @FXML
    private void update_TeacherEnrollment(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_enrollment_update(?,?,?)}");

            stmt.setString(1, this.teach_id.getText());
            stmt.setString(2, this.course_id.getText());
            stmt.setString(3, this.teach_enroll.getText());

            stmt.execute();
            setTeacherEnrTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherEnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            teach_id.setText("");
            course_id.setText("");
            teach_enroll.setText("");
        }

    }

    @FXML
    private void clear_TeacherEnrollment(ActionEvent actionEvent) {
        teach_id.setText("");
        course_id.setText("");
        teach_enroll.setText("");
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
