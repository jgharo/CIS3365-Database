package Information;
import Information.DBconnect;
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

public class MasterAdminController implements Initializable {

    @FXML
    public TableView <MasterAdmin> madminTable;
    public TableColumn <MasterAdmin, String> idCol;
    public TableColumn <MasterAdmin, String> firstCol;
    public TableColumn <MasterAdmin, String> lastCol;
    public TableColumn <MasterAdmin, String>phoneCol;
    public TableColumn <MasterAdmin, String> emailCol;
    public TableColumn <MasterAdmin, String> dateCol;

    @FXML
    public TextField id;
    public TextField firstText;
    public TextField lastText;
    public TextField phoneText;
    public TextField emailText;

    @FXML
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button menuButton;

    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<MasterAdmin> observableList = FXCollections.observableArrayList();
        madminTable.setItems(observableList);
        madminTable.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            MasterAdmin madmin = newval;
            if (madmin != null) {
                id.setText(madmin.getMAdm_ID());
                firstText.setText(madmin.getMAdm_FName());
                lastText.setText(madmin.getMAdm_LName());
                phoneText.setText(madmin.getMAdm_Phone());
                emailText.setText(madmin.getMAdm_Email());
                madminTable.refresh();
            }
        });
        madminTable.getSelectionModel().clearSelection();

        setMAdminTable();
        displayDatabase();

    }

    private void setMAdminTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("MAdm_ID"));
        firstCol.setCellValueFactory(new PropertyValueFactory<>("MAdm_FName"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("MAdm_LName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("MAdm_Email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("MAdm_Phone"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date_Mod"));
    }

    private void displayDatabase() {
        ObservableList<MasterAdmin> madminInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtMaster = con.prepareCall("{CALL master_admin_select()}");

            ResultSet rs = stmtMaster.executeQuery();
            while (rs.next()) {
                madminInfo.add(new MasterAdmin(rs.getString("madm_ID"), rs.getString("madm_FName"), rs.getString("madm_LName"),
                        rs.getString("madm_Email"), rs.getString("madm_Phone"), rs.getString("date_Mod")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(MasterAdmin.class.getName()).log(Level.SEVERE, null, ex);

        }
        madminTable.setItems(madminInfo);
    }

    public void addMasterAdmin(ActionEvent actionEvent) throws SQLException {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL master_admin_insert(?,?,?,?)}");

            stmtAdmin.setString(1, firstText.getText());
            stmtAdmin.setString(2, lastText.getText());
            stmtAdmin.setString(3, emailText.getText());
            stmtAdmin.setString(4, phoneText.getText());

            stmtAdmin.execute();
            setMAdminTable();
            displayDatabase();
            madminTable.getSelectionModel().clearSelection();
            madminTable.refresh();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(MasterAdmin.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            firstText.setText("");
            lastText.setText("");
            phoneText.setText("");
            emailText.setText("");
        }
    }

    public void updateMasterAdmin(ActionEvent actionEvent) {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL master_admin_update(?,?,?,?,?)}");

            stmtAdmin.setString(1, firstText.getText());
            stmtAdmin.setString(2, lastText.getText());
            stmtAdmin.setString(3, emailText.getText());
            stmtAdmin.setString(4, phoneText.getText());
            stmtAdmin.setString(5, id.getText());

            stmtAdmin.executeUpdate();
            setMAdminTable();
            displayDatabase();
            madminTable.getSelectionModel().clearSelection();
            madminTable.refresh();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(MasterAdmin.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            id.setText("");
            firstText.setText("");
            lastText.setText("");
            phoneText.setText("");
            emailText.setText("");

        }
    }


    public void deleteMasterAdmin(ActionEvent actionEvent) {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL master_admin_delete(?)}");

            stmtAdmin.setString(1, id.getText());

            stmtAdmin.execute();
            setMAdminTable();
            displayDatabase();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(MasterAdmin.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            firstText.setText("");
            lastText.setText("");
            phoneText.setText("");
            emailText.setText("");
            id.setText("");
        }
    }

    public void menu(ActionEvent actionEvent) {
        var stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(returnScene);
    }

    public void clearMasterAdmin(ActionEvent actionEvent) {
        firstText.setText("");
        lastText.setText("");
        phoneText.setText("");
        emailText.setText("");
        id.setText("");
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
