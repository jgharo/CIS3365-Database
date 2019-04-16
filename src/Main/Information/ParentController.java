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

public class ParentController implements Initializable {

    @FXML
    private TableView<Parents> table;
    @FXML
    private TableColumn<Parents, String> col_par_id;
    @FXML
    private TableColumn<Parents , String> col_fath_fname;
    @FXML
    private TableColumn<Parents , String> col_fath_lname;
    @FXML
    private TableColumn<Parents , String> col_fath_dob;
    @FXML
    private TableColumn<Parents , String> col_fath_email;
    @FXML
    private TableColumn<Parents , String> col_fath_phone;
    @FXML
    private TableColumn<Parents , String> col_fath_address;
    @FXML
    private TableColumn<Parents , String> col_fath_lang;
    @FXML
    private TableColumn<Parents , String> col_moth_fname;
    @FXML
    private TableColumn<Parents , String> col_moth_lname;
    @FXML
    private TableColumn<Parents , String> col_moth_dob;
    @FXML
    private TableColumn<Parents , String> col_moth_email;
    @FXML
    private TableColumn<Parents , String> col_moth_phone;
    @FXML
    private TableColumn<Parents , String> col_moth_address;
    @FXML
    private TableColumn<Parents, String> col_moth_lang;
    @FXML
    private TableColumn<Parents, String> col_par_marital;
    @FXML
    private TableColumn<Parents, String> col_amt_stu;
    @FXML
    private TableColumn<Parents, String> col_date_mod;


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

        ObservableList<Parents> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) ->  {

            Parents parent = newval;
            if (parent != null) {
                par_id.setText(parent.getPar_ID());
                par_marital.setText(parent.getPar_Marital());
                amt_stu.setText(parent.getAmt_Stu());

                fath_fname.setText(parent.getFath_FName());
                fath_lname.setText(parent.getFath_LName());
                fath_dob.setText(parent.getFath_DOB());
                fath_email.setText(parent.getFath_Email());
                fath_phone.setText(parent.getFath_Phone());
                fath_address.setText(parent.getFath_Address());
                fath_lang.setText(parent.getFath_Lang());

                moth_fname.setText(parent.getMoth_FName());
                moth_lname.setText(parent.getMoth_LName());
                moth_dob.setText(parent.getMoth_DOB());
                moth_email.setText(parent.getMoth_Email());
                moth_phone.setText(parent.getMoth_Phone());
                moth_address.setText(parent.getMoth_Address());
                moth_lang.setText(parent.getMoth_Lang());
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
        ObservableList<Parents> parentInfo= FXCollections.observableArrayList();

        try{
            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL parent_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()) {
                parentInfo.add(new Parents(rs.getString("par_ID"),rs.getString("fath_FName"), rs.getString("fath_LName"), rs.getString("fath_DOB"), rs.getString("fath_Email"), rs.getString("fath_Phone"),
                        rs.getString("fath_Address"), rs.getString("fath_Lang"), rs.getString("moth_FName"), rs.getString("moth_LName"), rs.getString("moth_DOB"), rs.getString("moth_Email"), rs.getString("moth_Phone"),
                        rs.getString("moth_Address"), rs.getString("moth_Lang"), rs.getString("par_Marital"), rs.getString("amt_Stu"), rs.getString("date_Mod")));
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

            CallableStatement stmt = con.prepareCall("{CALL parent_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            stmt.setString(1, fath_fname.getText());
            stmt.setString(2, fath_lname.getText());
            stmt.setString(3, fath_dob.getText());
            stmt.setString(4, fath_address.getText());
            stmt.setString(5, fath_email.getText());
            stmt.setString(6, fath_phone.getText());
            stmt.setString(7, fath_lang.getText());

            stmt.setString(8, moth_fname.getText());
            stmt.setString(9, moth_lname.getText());
            stmt.setString(10, moth_dob.getText());
            stmt.setString(11, moth_address.getText());
            stmt.setString(12, moth_email.getText());
            stmt.setString(13, moth_phone.getText());
            stmt.setString(14, moth_lang.getText());
            stmt.setString(15, par_marital.getText());
            stmt.setString(16, amt_stu.getText());

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
            fath_fname.setText("");
            fath_lname.setText("");
            fath_dob.setText("");
            fath_email.setText("");
            fath_phone.setText("");
            fath_address.setText("");
            fath_lang.setText("");
            moth_fname.setText("");
            moth_lname.setText("");
            moth_dob.setText("");
            moth_email.setText("");
            moth_phone.setText("");
            moth_address.setText("");
            moth_lang.setText("");
            par_marital.setText("");
            amt_stu.setText("");

        }

    }

    @FXML
    private void delete_Parent(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL parent_delete(?)}");

            stmt.setString(1, par_id.getText());

            stmt.execute();
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

            CallableStatement stmt = con.prepareCall("{CALL parent_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

           stmt.setString(1, fath_fname.getText());
            stmt.setString(2, fath_lname.getText());
            stmt.setString(3, fath_dob.getText());
            stmt.setString(4, fath_email.getText());
            stmt.setString(5, fath_phone.getText());
            stmt.setString(6, fath_address.getText());
            stmt.setString(7, fath_lang.getText());

            stmt.setString(8, moth_fname.getText());
            stmt.setString(9, moth_lname.getText());
            stmt.setString(10, moth_dob.getText());
            stmt.setString(11, moth_email.getText());
            stmt.setString(12, moth_phone.getText());
            stmt.setString(13, moth_address.getText());
            stmt.setString(14, moth_lang.getText());

            stmt.setString(15, par_marital.getText());
            stmt.setString(16, par_id.getText());


            stmt.executeUpdate();
            setParentTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{

            par_id.setText("");
            fath_fname.setText("");
            fath_lname.setText("");
            fath_dob.setText("");
            fath_email.setText("");
            fath_phone.setText("");
            fath_address.setText("");
            fath_lang.setText("");
            moth_fname.setText("");
            moth_lname.setText("");
            moth_dob.setText("");
            moth_email.setText("");
            moth_phone.setText("");
            moth_address.setText("");
            moth_lang.setText("");
            par_marital.setText("");
            amt_stu.setText("");
        }

    }

    public void clearParent(ActionEvent actionEvent){

        par_id.setText("");
        fath_fname.setText("");
        fath_lname.setText("");
        fath_dob.setText("");
        fath_email.setText("");
        fath_phone.setText("");
        fath_address.setText("");
        fath_lang.setText("");
        moth_fname.setText("");
        moth_lname.setText("");
        moth_dob.setText("");
        moth_email.setText("");
        moth_phone.setText("");
        moth_address.setText("");
        moth_lang.setText("");
        par_marital.setText("");
        amt_stu.setText("");
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
