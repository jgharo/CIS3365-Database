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

public class ParentRegistrationFormController implements Initializable {

    @FXML
    private TableView<ParentRegistrationForm> table;
    @FXML
    private TableColumn<ParentRegistrationForm, String> col_preg_id;
    @FXML
    private TableColumn<ParentRegistrationForm, String> col_par_id;
    @FXML
    private TableColumn<ParentRegistrationForm, String> col_preg_date;

    @FXML
    private TextField preg_id;
    @FXML
    private TextField par_id;
    @FXML
    private TextField preg_date;


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ParentRegistrationForm> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            ParentRegistrationForm stu = newval;
            if (stu != null) {
                preg_id.setText(stu.getPReg_ID());
                par_id.setText(stu.getPar_ID());
                preg_date.setText(stu.getPReg_Date());


            }
        });
        table.getSelectionModel().clearSelection();

        setParentRegTable();
        displayDatabase();

    }

    private void setParentRegTable() {
        col_preg_id.setCellValueFactory(new PropertyValueFactory<>("PReg_ID"));
        col_par_id.setCellValueFactory(new PropertyValueFactory<>("Par_ID"));
        col_preg_date.setCellValueFactory(new PropertyValueFactory<>("PReg_Date"));
    }


    private void displayDatabase(){

        ObservableList<ParentRegistrationForm> parentregInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL parent_reg_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                parentregInfo.add(new ParentRegistrationForm(rs.getString("PReg_ID"),rs.getString("Par_ID"), rs.getString("PReg_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(ParentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(parentregInfo);

    }

    @FXML
    private void add_ParentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL parent_reg_insert(?,?)}");

            stmt.setString(1, this.par_id.getText());
            stmt.setString(2, this.preg_date.getText());
            stmt.execute();
            setParentRegTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(ParentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            preg_id.setText("");
            par_id.setText("");
            preg_date.setText("");
        }

    }

    @FXML
    private void delete_ParentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL parent_reg_delete(?)}");

            stmtstu.setString(1, this.preg_id.getText());

            stmtstu.execute();
            setParentRegTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            preg_id.setText("");
            par_id.setText("");
            preg_date.setText("");
        }

    }

    @FXML
    private void update_ParentRegistrationForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL parent_reg_update(?,?,?)}");

            stmt.setString(1, this.preg_id.getText());
            stmt.setString(2, this.par_id.getText());
            stmt.setString(3, this.preg_date.getText());

            stmt.execute();
            setParentRegTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            preg_id.setText("");
            par_id.setText("");
            preg_date.setText("");
        }

    }

    @FXML
    private void clear_ParentRegistrationForm(ActionEvent actionEvent) {
        preg_id.setText("");
        par_id.setText("");
        preg_date.setText("");
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
