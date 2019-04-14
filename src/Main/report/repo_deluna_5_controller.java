package report;
import Information.DBconnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class repo_deluna_5_controller implements Initializable {

    @FXML
    private TableView<repo_deluna_5> table;
    @FXML
    private TableColumn<repo_deluna_5, String> col_adm_id;
    @FXML
    private TableColumn<repo_deluna_5, String> col_adm_user;
    @FXML
    private TableColumn<repo_deluna_5, String> col_adm_fname;
    @FXML
    private TableColumn<repo_deluna_5, String> col_adm_lname;
    @FXML
    private TableColumn<repo_deluna_5, String> col_adm_verify;
    @FXML
    private TableColumn<repo_deluna_5, String> col_areg_date;

    ObservableList<repo_deluna_5> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_deluna_5()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_deluna_5(rs.getString("Adm_ID"), rs.getString("Adm_User"),rs.getString("Adm_FName"), rs.getString("Adm_LName"), rs.getString("Adm_Verify"),
                        rs.getString("AReg_Date")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_deluna_5.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_adm_user.setCellValueFactory(new PropertyValueFactory<>("Adm_User"));
        col_adm_fname.setCellValueFactory(new PropertyValueFactory<>("Adm_FName"));
        col_adm_lname.setCellValueFactory(new PropertyValueFactory<>("Adm_LName"));
        col_adm_verify.setCellValueFactory(new PropertyValueFactory<>("Adm_Verify"));
        col_areg_date.setCellValueFactory(new PropertyValueFactory<>("AReg_Date"));

        table.setItems(oblist);


    }
}
