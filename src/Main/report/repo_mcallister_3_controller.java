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

public class repo_mcallister_3_controller implements Initializable {

    @FXML
    private TableView<repo_mcallister_3> table;
    @FXML
    private TableColumn<repo_mcallister_3, String> col_stu_id;
    @FXML
    private TableColumn<repo_mcallister_3, String> col_stu_fname;
    @FXML
    private TableColumn<repo_mcallister_3, String> col_stu_lname;
    @FXML
    private TableColumn<repo_mcallister_3, String> col_stu_phone;
    @FXML
    private TableColumn<repo_mcallister_3, String> col_stu_email;
    @FXML
    private TableColumn<repo_mcallister_3, String> col_stu_verify;
    @FXML
    private TableColumn<repo_mcallister_3, String> col_trans_remain;

    ObservableList<repo_mcallister_3> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_mcallister_3()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_mcallister_3(rs.getString("Stu_ID"), rs.getString("Stu_FName"), rs.getString("Stu_LName"), rs.getString("Stu_Phone"),
                        rs.getString("Stu_Email"),rs.getString("Stu_Verify"),rs.getString("Trans_Remain")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_mcallister_3.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_stu_fname.setCellValueFactory(new PropertyValueFactory<>("Stu_FName"));
        col_stu_lname.setCellValueFactory(new PropertyValueFactory<>("Stu_LName"));
        col_stu_phone.setCellValueFactory(new PropertyValueFactory<>("Stu_Phone"));
        col_stu_email.setCellValueFactory(new PropertyValueFactory<>("Stu_Email"));
        col_stu_verify.setCellValueFactory(new PropertyValueFactory<>("Stu_Verify"));
        col_trans_remain.setCellValueFactory(new PropertyValueFactory<>("Trans_Remain"));



        table.setItems(oblist);


    }
}
