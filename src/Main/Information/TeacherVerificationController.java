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


    ObservableList<TeacherVerification> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL teacher_verification_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new TeacherVerification(rs.getString("Adm_ID"),rs.getString("Teach_ID"), rs.getString("Teach_Verify")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TeacherVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_teach_verify.setCellValueFactory(new PropertyValueFactory<>("Teach_Verify"));

        table.setItems(oblist);

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
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TeacherVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_TeacherVerification(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL teacher_verification_delete(?)}");

            stmtstu.setString(1, this.teach_id.getText());

            stmtstu.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherVerificationController.class.getName()).log(Level.SEVERE, null, ex);
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
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherVerificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
