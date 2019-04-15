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


    ObservableList<TeacherAccount> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL teacher_account_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new TeacherAccount(rs.getString("Teach_Acc_ID"),rs.getString("Teach_ID"), rs.getString("Teach_User"), rs.getString("Teach_Pass")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TeacherAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_teach_acc_id.setCellValueFactory(new PropertyValueFactory<>("Teach_Acc_ID"));
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_teach_user.setCellValueFactory(new PropertyValueFactory<>("Teach_User"));
        col_teach_pass.setCellValueFactory(new PropertyValueFactory<>("Teach_Pass"));

        table.setItems(oblist);

    }

    @FXML
    private void add_TeacherAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_account_insert(?,?,?)}");

            stmt.setString(1, this.teach_id.getText());
            stmt.setString(2, this.teach_user.getText());
            stmt.setString(3, this.teach_pass.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TeacherAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_TeacherAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_account_delete(?)}");

            stmt.setString(1, this.teach_acc_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_TeacherAccount(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL teacher_account_update(?,?,?,?)}");

            stmt.setString(1, this.teach_acc_id.getText());
            stmt.setString(2, this.teach_id.getText());
            stmt.setString(3, this.teach_user.getText());
            stmt.setString(4, this.teach_pass.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TeacherAccountController.class.getName()).log(Level.SEVERE, null, ex);
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
