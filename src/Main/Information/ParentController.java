/**package Information;

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

public class ParentController implements Initializable {

    @FXML
    private TableView<Parent> table;
    @FXML
    private TableColumn<Parent, String> col_par_id;
    @FXML
    private TableColumn<Parent , String> col_fath_fname;
    @FXML
    private TableColumn<Parent , String> col_fath_lname;
    @FXML
    private TableColumn<Parent , String> col_fath_dob;
    @FXML
    private TableColumn<Parent , String> col_fath_email;
    @FXML
    private TableColumn<Parent , String> col_fath_phone;
    @FXML
    private TableColumn<Parent , String> col_fath_address;
    @FXML
    private TableColumn<Parent , String> col_fath_lang;
    @FXML
    private TableColumn<Parent , String> col_moth_fname;
    @FXML
    private TableColumn<Parent , String> col_moth_lname;
    @FXML
    private TableColumn<Parent , String> col_moth_dob;
    @FXML
    private TableColumn<Parent , String> col_moth_email;
    @FXML
    private TableColumn<Parent , String> col_moth_phone;
    @FXML
    private TableColumn<Parent , String> col_moth_address;
    @FXML
    private TableColumn<Parent, String> col_moth_lang;
    @FXML
    private TableColumn<Parent, String> col_par_marital;
    @FXML
    private TableColumn<Parent, String> col_amt_stu;
    @FXML
    private TableColumn<Parent, String> col_date_mod;


    @FXML
    private TextField par_id;
    @FXML
    private TextField fath_fname;
    @FXML
    private TextField fath_lname;
    @FXML
    private TextField fath_dob;
    @FXML
    private TextField fath_email;
    @FXML
    private TextField fath_phone;
    @FXML
    private TextField fath_address;
    @FXML
    private TextField fath_lang;
    @FXML
    private TextField moth_fname;
    @FXML
    private TextField moth_lname;
    @FXML
    private TextField moth_dob;
    @FXML
    private TextField moth_email;
    @FXML
    private TextField moth_phone;
    @FXML
    private TextField moth_address;
    @FXML
    private TextField moth_lang;
    @FXML
    private TextField par_marital;
    @FXML
    private TextField amt_stu;

    Scene returnScene;


    @Override
    public void initialize(URL location, ResourceBundle resources){

        ObservableList<Parent> observableList = FXCollections.observableArrayList();

        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldval, newval) ->  {

            Parent parent = newval;
            if (parent != null) {
                this.par_id.setText(parent.getPar_ID());
                fath_fname.setText(parent.getFath_FName());
                fath_lname.setText(parent.getFath_LName());
                fath_dob.setText(parent.getFath_DOB());
                fath_email.setText(parent.getFath_Email());
                fath_phone.setText(parent.getFath_Phone());
                fath_address.setText(parent.getFath_Address());

                moth_fname.setText(parent.getMoth_FName());
                moth_lname.setText(parent.getMoth_LName());
                moth_dob.setText(parent.getMoth_DOB());
                moth_email.setText(parent.getMoth_Email());
                moth_phone.setText(parent.getMoth_Phone());
                moth_address.setText(parent.getMoth_Address());

                par_marital.setText(parent.getPar_Marital());
                amt_stu.setText(parent.getAmt_Stu());


            }
        });
        table.getSelectionModel().clearSelection();

        setParentTable();
        displayDatabase();
    }

    private void setParentTable() {

        col_par_id.setCellValueFactory(new PropertyValueFactory<>("Par_ID"));
        col_fath_fname.setCellValueFactory(new PropertyValueFactory<>("Fath_FName"));
        col_fath_lname.setCellValueFactory(new PropertyValueFactory<>("Fath_LName"));
        col_fath_dob.setCellValueFactory(new PropertyValueFactory<>("Fath_DOB"));
        col_fath_email.setCellValueFactory(new PropertyValueFactory<>("Fath_Email"));
        col_fath_phone.setCellValueFactory(new PropertyValueFactory<>("Fath_Phone"));
        col_fath_address.setCellValueFactory(new PropertyValueFactory<>("Fath_Address"));
        col_fath_lang.setCellValueFactory(new PropertyValueFactory<>("Fath_Lang"));

        col_moth_fname.setCellValueFactory(new PropertyValueFactory<>("Moth_FName"));
        col_moth_lname.setCellValueFactory(new PropertyValueFactory<>("Moth_LName"));
        col_moth_dob.setCellValueFactory(new PropertyValueFactory<>("Moth_DOB"));
        col_moth_email.setCellValueFactory(new PropertyValueFactory<>("Moth_Email"));
        col_moth_phone.setCellValueFactory(new PropertyValueFactory<>("Moth_Phone"));
        col_moth_address.setCellValueFactory(new PropertyValueFactory<>("Moth_Address"));
        col_moth_lang.setCellValueFactory(new PropertyValueFactory<>("Moth_Lang"));

        col_par_marital.setCellValueFactory(new PropertyValueFactory<>("Par_Marital"));
        col_amt_stu.setCellValueFactory(new PropertyValueFactory<>("Amt_Stu"));

        col_date_mod.setCellValueFactory(new PropertyValueFactory<>("Date_Mod"));
    }


    private void displayDatabase(){
        ObservableList<Parent> parentInfo= FXCollections.observableArrayList();

        try{
            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL parent_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()) {
                parentInfo.add(new Parent(rs.getString("Par_ID"),
                        rs.getString("Fath_FName"), rs.getString("Fath_LName"), rs.getString("Fath_DOB"), rs.getString("Fath_Email"), rs.getString("Fath_Phone"),
                        rs.getString("Fath_Address"), rs.getString("Fath_Lang"), rs.getString("Moth_FName"), rs.getString("Moth_LName"), rs.getString("Moth_DOB"), rs.getString("Moth_Email"), rs.getString("Moth_Phone"),
                        rs.getString("Moth_Address"), rs.getString("Moth_Lang"), rs.getString("Par_Marital"), rs.getString("Amt_Stu"), rs.getString("Date_Mod")));
            }


        } catch (SQLException ex) {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);

        }

        table.setItems(parentInfo);


    }




    @FXML
    private void add_Parent(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL student_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            stmt.setString(1, this.fath_fname.getText());
            stmt.setString(2, this.fath_lname.getText());
            stmt.setString(3, this.fath_dob.getText());
            stmt.setString(4, this.fath_email.getText());
            stmt.setString(5, this.fath_phone.getText());
            stmt.setString(6, this.fath_address.getText());
            stmt.setString(7, this.fath_lang.getText());

            stmt.setString(8, this.moth_fname.getText());
            stmt.setString(9, this.moth_lname.getText());
            stmt.setString(10, this.moth_dob.getText());
            stmt.setString(11, this.moth_email.getText());
            stmt.setString(12, this.moth_phone.getText());
            stmt.setString(13, this.moth_address.getText());
            stmt.setString(14, this.moth_lang.getText());
            stmt.setString(15, this.par_marital.getText());
            stmt.setString(16, this.amt_stu.getText());

            stmt.execute();
            setParentTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{

            this.fath_fname.setText("");
            this.fath_lname.setText("");
            this.fath_dob.setText("");
            this.fath_email.setText("");
            this.fath_phone.setText("");
            this.fath_address.setText("");
            this.fath_lang.setText("");
            this.moth_fname.setText("");
            this.moth_lname.setText("");
            this.moth_dob.setText("");
            this.moth_email.setText("");
            this.moth_phone.setText("");
            this.moth_address.setText("");
            this.moth_lang.setText("");
            this.par_marital.setText("");
            this.amt_stu.setText("");

        }

    }

    @FXML
    private void delete_Parent(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL parent_delete(?)}");

            stmtstu.setString(1, this.par_id.getText());

            stmtstu.execute();
            setParentTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            par_id.setText("");
        }

    }

    @FXML
    private void update_Parent(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL parent_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            stmt.setString(1, this.par_id.getText());
            stmt.setString(2, this.fath_fname.getText());
            stmt.setString(3, this.fath_lname.getText());
            stmt.setString(4, this.fath_dob.getText());
            stmt.setString(5, this.fath_email.getText());
            stmt.setString(6, this.fath_phone.getText());
            stmt.setString(7, this.fath_address.getText());
            stmt.setString(8, this.fath_lang.getText());

            stmt.setString(9, this.moth_fname.getText());
            stmt.setString(10, this.moth_lname.getText());
            stmt.setString(11, this.moth_dob.getText());
            stmt.setString(12, this.moth_email.getText());
            stmt.setString(13, this.moth_phone.getText());
            stmt.setString(14, this.moth_address.getText());
            stmt.setString(15, this.moth_lang.getText());

            stmt.setString(16, this.par_marital.getText());
            stmt.setString(17, this.amt_stu.getText());


            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{

            this.par_id.setText("");
            this.fath_fname.setText("");
            this.fath_lname.setText("");
            this.fath_dob.setText("");
            this.fath_email.setText("");
            this.fath_phone.setText("");
            this.fath_address.setText("");
            this.fath_lang.setText("");
            this.moth_fname.setText("");
            this.moth_lname.setText("");
            this.moth_dob.setText("");
            this.moth_email.setText("");
            this.moth_phone.setText("");
            this.moth_address.setText("");
            this.moth_lang.setText("");
            this.par_marital.setText("");
            this.amt_stu.setText("");
        }

    }

    public void clearParent(ActionEvent actionEvent){

        this.par_id.setText("");
        this.fath_fname.setText("");
        this.fath_lname.setText("");
        this.fath_dob.setText("");
        this.fath_email.setText("");
        this.fath_phone.setText("");
        this.fath_address.setText("");
        this.fath_lang.setText("");
        this.moth_fname.setText("");
        this.moth_lname.setText("");
        this.moth_dob.setText("");
        this.moth_email.setText("");
        this.moth_phone.setText("");
        this.moth_address.setText("");
        this.moth_lang.setText("");
        this.par_marital.setText("");
        this.amt_stu.setText("");
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
*/