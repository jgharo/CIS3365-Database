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
import javafx.scene.control.Button;
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

public class TeacherAccountController implements Initializable {

    @FXML
    private TableView<TeacherAccount> table;
    @FXML
    private TableColumn<TeacherAccount,String> col_teach_acc_id;
    @FXML
    private TableColumn<TeacherAccount, String> col_teach_id;
    @FXML
    private TableColumn<TeacherAccount, String> col_teach_user;
    @FXML
    private TableColumn<TeacherAccount, String> col_teach_pass;

    @FXML
    private TextField teach_acc_id;
    @FXML
    private TextField teach_id;
    @FXML
    private TextField teach_user;
    @FXML
    private TextField teach_pass;

    Scene returnScene;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ObservableList<TeacherAccount> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            TeacherAccount admin = newval;
            if (admin != null) {
                teach_acc_id.setText(admin.getTeach_Acc_ID());
                teach_id.setText(admin.getTeach_ID());
                teach_user.setText(admin.getTeach_User());
                teach_pass.setText(admin.getTeach_Pass());
            }
        });
        table.getSelectionModel().clearSelection();

        setTeacherAccountTable();
        displayDatabase();
    }

    private void setTeacherAccountTable() {
        col_teach_acc_id.setCellValueFactory(new PropertyValueFactory<>("Teach_Acc_ID"));
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_teach_user.setCellValueFactory(new PropertyValueFactory<>("Teach_User"));
        col_teach_pass.setCellValueFactory(new PropertyValueFactory<>("Teach_Pass"));
    }

    private void displayDatabase() {
        ObservableList<TeacherAccount> teachaccInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL teacher_account_select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                teachaccInfo.add(new TeacherAccount(rs.getString("Teach_Acc_ID"), rs.getString("Teach_ID"), rs.getString("Teach_User"),
                        rs.getString("Teach_Pass")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(teachaccInfo);
    }

    @FXML
    private void add_TeacherAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_account_insert(?,?,?)}");

            stmt.setString(1, teach_id.getText());
            stmt.setString(2, teach_user.getText());
            stmt.setString(3, teach_pass.getText());

            stmt.execute();
            setTeacherAccountTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        } catch (SQLException ex){
            Logger.getLogger(TeacherAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            teach_id.setText("");
            teach_user.setText("");
            teach_pass.setText("");
        }
    }

    @FXML
    private void delete_TeacherAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_account_delete(?)}");

            stmt.setString(1, teach_acc_id.getText());

            stmt.execute();
            setTeacherAccountTable();
            displayDatabase();
            con.close();
        }
        catch (SQLException ex){
            Logger.getLogger(TeacherAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
           teach_acc_id.setText("");
        }

    }

    @FXML
    private void update_TeacherAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_account_update(?,?,?,?)}");

            stmt.setString(1, teach_acc_id.getText());
            stmt.setString(2, teach_id.getText());
            stmt.setString(3, teach_user.getText());
            stmt.setString(4, teach_pass.getText());

            stmt.executeUpdate();
            setTeacherAccountTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TeacherAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            teach_acc_id.setText("");
            teach_id.setText("");
            teach_user.setText("");
            teach_pass.setText("");
        }

    }

    public void clearTeacherAccount (ActionEvent actionEvent) {
        teach_acc_id.setText("");
        teach_id.setText("");
        teach_user.setText("");
        teach_pass.setText("");
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
