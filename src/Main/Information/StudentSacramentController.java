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

public class StudentSacramentController implements Initializable {

    @FXML
    private TableView<StudentSacrament> table;
    @FXML
    private TableColumn<StudentSacrament, String> col_sac_id;
    @FXML
    private TableColumn<StudentSacrament, String> col_stu_id;
    @FXML
    private TableColumn<StudentSacrament, String> col_sac_date;


    @FXML
    private TextField sac_id;
    @FXML
    private TextField stu_id;
    @FXML
    private TextField sac_date;


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<StudentSacrament> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            StudentSacrament stu = newval;
            if (stu != null) {
                sac_id.setText(stu.getSac_ID());
                stu_id.setText(stu.getStu_ID());
                sac_date.setText(stu.getSac_Date());

            }
        });
        table.getSelectionModel().clearSelection();

        setStudentSacramentTable();
        displayDatabase();

    }

    private void setStudentSacramentTable() {
        col_sac_id.setCellValueFactory(new PropertyValueFactory<>("Sac_ID"));
        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_sac_date.setCellValueFactory(new PropertyValueFactory<>("Sac_Date"));

    }


    private void displayDatabase(){

        ObservableList<StudentSacrament> sacInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL student_sacrament_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                sacInfo.add(new StudentSacrament(rs.getString("Sac_ID"),rs.getString("Stu_ID"),rs.getString("Sac_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(StudentSacramentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(sacInfo);

    }

    @FXML
    private void add_StudentSacrament(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL student_sacrament_insert(?,?,?)}");

            stmt.setString(1, this.sac_id.getText());
            stmt.setString(2, this.stu_id.getText());
            stmt.setString(3, this.sac_date.getText());
            stmt.execute();
            setStudentSacramentTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(StudentSacramentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            sac_id.setText("");
            stu_id.setText("");
            sac_date.setText("");
        }

    }

    @FXML
    private void delete_StudentSacrament(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL student_sacrament_delete(?)}");

            stmtstu.setString(1, this.stu_id.getText());

            stmtstu.execute();
            setStudentSacramentTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(SacramentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            stu_id.setText("");
        }

    }

    @FXML
    private void update_StudentSacrament(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL student_sacrament_update(?,?,?)}");

            stmt.setString(1, this.sac_id.getText());
            stmt.setString(2, this.stu_id.getText());
            stmt.setString(3, this.sac_date.getText());

            stmt.execute();
            setStudentSacramentTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentSacramentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            sac_id.setText("");
            stu_id.setText("");
            sac_date.setText("");
        }

    }

    @FXML
    private void clear_StudentSacramentForm(ActionEvent actionEvent) {
        sac_id.setText("");
        stu_id.setText("");
        sac_date.setText("");
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
