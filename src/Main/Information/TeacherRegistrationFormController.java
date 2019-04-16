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

public class TeacherRegistrationFormController implements Initializable {

    @FXML
    private TableView<TeacherRegistrationForm> table;
    @FXML
    private TableColumn<TeacherRegistrationForm, String> col_treg_id;
    @FXML
    private TableColumn<TeacherRegistrationForm, String> col_teach_id;
    @FXML
    private TableColumn<TeacherRegistrationForm, String> col_treg_date;

    @FXML
    private TextField treg_id;
    @FXML
    private TextField teach_id;
    @FXML
    private TextField treg_date;


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TeacherRegistrationForm> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            TeacherRegistrationForm stu = newval;
            if (stu != null) {
                treg_id.setText(stu.getTReg_ID());
                teach_id.setText(stu.getTeach_ID());
                treg_date.setText(stu.getTReg_Date());


            }
        });
        table.getSelectionModel().clearSelection();

        setTeacherRegTable();
        displayDatabase();

    }

    private void setTeacherRegTable() {
        col_treg_id.setCellValueFactory(new PropertyValueFactory<>("TReg_ID"));
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_treg_date.setCellValueFactory(new PropertyValueFactory<>("TReg_Date"));
    }


    private void displayDatabase(){

        ObservableList<TeacherRegistrationForm> teacherregInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL teacher_reg_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                teacherregInfo.add(new TeacherRegistrationForm(rs.getString("TReg_ID"),rs.getString("Teach_ID"), rs.getString("TReg_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TeacherRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(teacherregInfo);

    }

    @FXML
    private void add_TeacherRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_reg_insert(?,?)}");

            stmt.setString(1, this.teach_id.getText());
            stmt.setString(2, this.treg_date.getText());
            stmt.execute();
            setTeacherRegTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TeacherRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            treg_id.setText("");
            teach_id.setText("");
            treg_date.setText("");
        }

    }

    @FXML
    private void delete_TeacherRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL teacher_reg_delete(?)}");

            stmtstu.setString(1, this.treg_id.getText());

            stmtstu.execute();
            setTeacherRegTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            treg_id.setText("");
            teach_id.setText("");
            treg_date.setText("");
        }

    }

    @FXML
    private void update_TeacherRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_reg_update(?,?,?)}");

            stmt.setString(1, this.treg_id.getText());
            stmt.setString(2, this.teach_id.getText());
            stmt.setString(3, this.treg_date.getText());

            stmt.execute();
            setTeacherRegTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            treg_id.setText("");
            teach_id.setText("");
            treg_date.setText("");
        }

    }

    @FXML
    private void clear_TeacherRegistrationForm(ActionEvent actionEvent) {
        treg_id.setText("");
        teach_id.setText("");
        treg_date.setText("");
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
