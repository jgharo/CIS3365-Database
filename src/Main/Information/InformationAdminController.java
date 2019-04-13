package Information;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformationAdminController  implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<AdminInfo> adminInfo = FXCollections.observableArrayList();
        /**
         try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL admin_Select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                adminInfo.add(new display_student_model(rs.getString("MAdm_ID"), rs.getString("Adm_FName"), rs.getString("Adm_LName"), rs.getString("Adm_DOB"),
                        rs.getString("Adm_Phone"), rs.getString("Adm_Address"), rs.getString("Adm_Email")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(display_student_controller.class.getName()).log(Level.SEVERE, null, ex);

        }
         */
    }

}