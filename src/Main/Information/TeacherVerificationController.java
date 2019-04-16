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

public class TeacherVerificationController implements Initializable {

    @FXML
    private TableView<TeacherVerification> table;
    @FXML
    private TableColumn<TeacherVerification,String> col_adm_id;
    @FXML
    private TableColumn<TeacherVerification, String> col_teach_id;
    @FXML
    private TableColumn<TeacherVerification, String> col_teach_verify;

    @FXML
    private TextField adm_id;
    @FXML
    private TextField teach_id;
    @FXML
    private TextField teach_verify;


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TeacherVerification> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            TeacherVerification stu = newval;
            if (stu != null) {
                adm_id.setText(stu.getAdm_ID());
                teach_id.setText(stu.getTeach_ID());
                teach_verify.setText(stu.getTeach_Verify());


            }
        });
        table.getSelectionModel().clearSelection();

        setTeacherVerTable();
        displayDatabase();

    }

    private void setTeacherVerTable() {
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_teach_verify.setCellValueFactory(new PropertyValueFactory<>("Teach_Verify"));
    }


    private void displayDatabase(){

        ObservableList<TeacherVerification> teacherverInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL teacher_verification_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                teacherverInfo.add(new TeacherVerification(rs.getString("Adm_ID"),rs.getString("Teach_ID"), rs.getString("Teach_Verify")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TeacherVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(teacherverInfo);

    }

    @FXML
    private void add_TeacherVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_verification_insert(?,?,?)}");

            stmt.setString(1, this.adm_id.getText());
            stmt.setString(2, this.teach_id.getText());
            stmt.setString(3, this.teach_verify.getText());
            stmt.execute();
            setTeacherVerTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TeacherVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            adm_id.setText("");
            teach_id.setText("");
            teach_verify.setText("");
        }

    }

    @FXML
    private void delete_TeacherVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL teacher_verification_delete(?)}");

            stmtstu.setString(1, this.teach_id.getText());

            stmtstu.execute();
            setTeacherVerTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            adm_id.setText("");
            teach_id.setText("");
            teach_verify.setText("");
        }

    }

    @FXML
    private void update_TeacherVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_verification_update(?,?,?)}");

            stmt.setString(1, this.adm_id.getText());
            stmt.setString(2, this.teach_id.getText());
            stmt.setString(3, this.teach_verify.getText());

            stmt.execute();
            setTeacherVerTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            adm_id.setText("");
            teach_id.setText("");
            teach_verify.setText("");
        }

    }

    @FXML
    private void clear_TeacherVerification(ActionEvent actionEvent) {
        adm_id.setText("");
        teach_id.setText("");
        teach_verify.setText("");
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
