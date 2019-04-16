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
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherController implements Initializable {

    @FXML
    public TableView <Teacher> table;
    public TableColumn <Teacher, String> id;
    public TableColumn <Teacher, String> fname;
    public TableColumn <Teacher, String> lname;
    public TableColumn <Teacher, String> dob;
    public TableColumn <Teacher, String> email;
    public TableColumn <Teacher, String> phone;
    public TableColumn <Teacher, String> address;
    public TableColumn <Teacher, String> lang;
    public TableColumn <Teacher, String> virtus;
    public TableColumn <Teacher, String> date;
    @FXML
    public TextField txtID;
    public TextField txtFirst;
    public TextField txtLast;
    public TextField txtDOB;
    public TextField txtEmail;
    public TextField txtPhone;
    public TextField txtAddress;
    public TextField txtLang;
    public TextField txtVirtus;

    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Teacher> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            Teacher teach = newval;
            if (teach != null) {
                txtID.setText(teach.getTeach_ID());
                txtFirst.setText(teach.getTeach_FName());
                txtLast.setText(teach.getTeach_LName());
                txtDOB.setText(teach.getTeach_DOB());
                txtEmail.setText(teach.getTeach_Email());
                txtPhone.setText(teach.getTeach_Phone());
                txtAddress.setText(teach.getTeach_Address());
                txtLang.setText(teach.getTeach_Lang());
                txtVirtus.setText(teach.getTeach_Virtus());
                table.refresh();
            }
        });
        table.getSelectionModel().clearSelection();

        setTeachTable();
        displayDatabase();

    }

    private void setTeachTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        fname.setCellValueFactory(new PropertyValueFactory<>("Teach_FName"));
        lname.setCellValueFactory(new PropertyValueFactory<>("Teach_LName"));
        dob.setCellValueFactory(new PropertyValueFactory<>("Teach_DOB"));
        email.setCellValueFactory(new PropertyValueFactory<>("Teach_Email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Teach_Phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("Teach_Address"));
        lang.setCellValueFactory(new PropertyValueFactory<>("Teach_Lang"));
        virtus.setCellValueFactory(new PropertyValueFactory<>("Teach_Virtus"));

    }

    private void displayDatabase() {
        ObservableList<Teacher> teachInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_Select()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                teachInfo.add(new Teacher(rs.getString("teach_ID"), rs.getString("teach_FName"), rs.getString("teach_LName"),
                        rs.getString("teach_DOB"), rs.getString("teach_Email"), rs.getString("teach_Phone"), rs.getString("teach_Address"), rs.getString("teach_Lang"), rs.getString("teach_Virtus"),rs.getString("date_Mod")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(teachInfo);
    }

    public void addTeach(ActionEvent actionEvent) throws SQLException {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmt = con.prepareCall("{CALL teacher_insert(?,?,?,?,?,?,?,?)}");

            stmt.setString(1, txtFirst.getText());
            stmt.setString(2, txtLast.getText());
            stmt.setString(3, txtAddress.getText());
            stmt.setString(4, txtDOB.getText());
            stmt.setString(5, txtEmail.getText());
            stmt.setString(6, txtPhone.getText());
            stmt.setString(7, txtLang.getText());
            stmt.setString(8, txtVirtus.getText());

            stmt.execute();
            setTeachTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            txtFirst.setText("");
            txtLast.setText("");
            txtAddress.setText("");
            txtDOB.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtLang.setText("");
            txtVirtus.setText("");
        }
    }

    public void updateTeach(ActionEvent actionEvent) {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmt = con.prepareCall("{CALL teacher_update(?,?,?,?,?,?,?,?,?)}");

            stmt.setString(1, txtFirst.getText());
            stmt.setString(2, txtLast.getText());
            stmt.setString(3, txtAddress.getText());
            stmt.setString(4, txtDOB.getText());
            stmt.setString(5, txtEmail.getText());
            stmt.setString(6, txtPhone.getText());
            stmt.setString(7, txtLang.getText());
            stmt.setString(8, txtVirtus.getText());
            stmt.setString(9, txtID.getText());

            stmt.executeUpdate();
            setTeachTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            txtFirst.setText("");
            txtLast.setText("");
            txtAddress.setText("");
            txtDOB.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtLang.setText("");
            txtVirtus.setText("");
            txtID.setText("");
        }
    }


    public void deleteTeach(ActionEvent actionEvent) {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL teacher_delete(?)}");

            stmtAdmin.setString(1, txtID.getText());

            stmtAdmin.execute();
            setTeachTable();
            displayDatabase();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            txtID.setText("");
        }
    }

    public void menu(ActionEvent actionEvent) {
        var stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(returnScene);
    }

    public void clearAdmin(ActionEvent actionEvent) {
        txtFirst.setText("");
        txtLast.setText("");
        txtAddress.setText("");
        txtDOB.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtLang.setText("");
        txtVirtus.setText("");
        txtID.setText("");
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
