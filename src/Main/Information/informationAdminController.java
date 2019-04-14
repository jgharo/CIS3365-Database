package Information;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class informationAdminController implements Initializable {

    @FXML
    public TableView <InformationAdmin> adminTable;
    public TableColumn <InformationAdmin, String> idCol;
    public TableColumn <InformationAdmin, String> firstCol;
    public TableColumn <InformationAdmin, String> lastCol;
    public TableColumn <InformationAdmin, String>phoneCol;
    public TableColumn <InformationAdmin, String> emailCol;
    public TableColumn <InformationAdmin, String> addressColl;
    public TableColumn <InformationAdmin, String> dateCol;

    @FXML
    public TextField firstText;
    public TextField lastText;
    public TextField phoneText;
    public TextField emailText;
    public TextField addressText;

    @FXML
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button menuButton;

    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn=null;
        Statement stmt=null;
        String connectionURL=
                "jdbc:sqlserver://localhost:1433;"
                        +"database=test_2;"
                        +"user=anson;"
                        +"password=password;";
        ObservableList<InformationAdmin> adminInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_Select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                adminInfo.add(new InformationAdmin(rs.getString("Adm_ID"), rs.getString("Adm_FName"), rs.getString("Adm_LName"), rs.getString("Adm_Email"),
                        rs.getString("Adm_Phone"), rs.getString("Adm_Address"),rs.getString("Date_Mod") ));


            }

        } catch (SQLException ex) {
            Logger.getLogger( InformationAdmin.class.getName()).log(Level.SEVERE, null, ex);

        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        firstCol.setCellValueFactory(new PropertyValueFactory<>("Adm_FName"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("Adm_LName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("Adm_Phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Adm_Address"));
        addressColl.setCellValueFactory(new PropertyValueFactory<>("Adm_Email"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date_Mod"));

        adminTable.setItems(adminInfo);


    }

    public void addAdmin(ActionEvent actionEvent) {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_insert()}");
            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                stmtAdmin.setString(1,this.firstText.getText());
                stmtAdmin.setString(2,this.lastText.getText());
                stmtAdmin.setString(3,this.phoneText.getText());
                stmtAdmin.setString(4,this.emailText.getText());
                stmtAdmin.setString(5,this.addressText.getText());

                stmtAdmin.execute();
                con.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger( InformationAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAdmin(ActionEvent actionEvent) {
        Connection conn=null;
        Statement stmt=null;
        String connectionURL=
                "jdbc:sqlserver://localhost:1433;"
                        +"database=test_2;"
                        +"user=anson;"
                        +"password=password;";
        try {
             conn = DriverManager.getConnection( connectionURL );

            CallableStatement stmtadm= conn.prepareCall("{CALL UpdateAdmin(?,?,?,?,?,?)}");

            stmtadm.setString(1,this.firstText.getText());
            stmtadm.setString(2,this.lastText.getText());
            stmtadm.setString(3,this.phoneText.getText());
            stmtadm.setString(4,this.emailText.getText());
            stmtadm.setString(5,this.addressText.getText());
           

            stmtadm.executeUpdate();
            System.out.println("updated admin");
            stmtadm.close();


        }
        catch (SQLException e){
            System.out.println("unable to make connection with db");
            e.printStackTrace();
        }
    }


    public void deleteAdmin(ActionEvent actionEvent) {
        try{
            Connection con = DBconnect.getConnection();
            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_delete()}");
            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                stmtAdmin.setString(1,this.firstText.getText());
                stmtAdmin.setString(2,this.lastText.getText());
                stmtAdmin.setString(3,this.phoneText.getText());
                stmtAdmin.setString(4,this.emailText.getText());
                stmtAdmin.setString(5,this.addressText.getText());

                stmtAdmin.execute();
                con.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger( InformationAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void menu(ActionEvent actionEvent) {
        var stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(returnScene);
    }
}
