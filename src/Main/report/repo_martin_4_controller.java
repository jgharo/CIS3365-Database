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

public class repo_martin_4_controller implements Initializable {

    @FXML
    private TableView<repo_martin_4> table;
    @FXML
    private TableColumn<repo_martin_4, String> col_teach_id;
    @FXML
    private TableColumn<repo_martin_4, String> col_teach_user;
    @FXML
    private TableColumn<repo_martin_4, String> col_teach_fname;
    @FXML
    private TableColumn<repo_martin_4, String> col_teach_lname;
    @FXML
    private TableColumn<repo_martin_4, String> col_teach_verify;
    @FXML
    private TableColumn<repo_martin_4, String> col_treg_date;

    ObservableList<repo_martin_4> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_martin_4()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_martin_4(rs.getString("Teach_ID"), rs.getString("Teach_User"),rs.getString("Teach_FName"), rs.getString("Teach_LName"), rs.getString("Teach_Verify"),
                        rs.getString("TReg_Date")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_martin_4.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_teach_user.setCellValueFactory(new PropertyValueFactory<>("Teach_User"));
        col_teach_fname.setCellValueFactory(new PropertyValueFactory<>("Teach_FName"));
        col_teach_lname.setCellValueFactory(new PropertyValueFactory<>("Teach_LName"));
        col_teach_verify.setCellValueFactory(new PropertyValueFactory<>("Teach_Verify"));
        col_treg_date.setCellValueFactory(new PropertyValueFactory<>("TReg_Date"));



        table.setItems(oblist);


    }
}
