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

public class repo_martin_5_controller implements Initializable {

    @FXML
    private TableView<repo_martin_5> table;
    @FXML
    private TableColumn<repo_martin_5, String> col_teach_id;
    @FXML
    private TableColumn<repo_martin_5, String> col_teach_fname;
    @FXML
    private TableColumn<repo_martin_5, String> col_teach_lname;
    @FXML
    private TableColumn<repo_martin_5, String> col_teach_verify;
    @FXML
    private TableColumn<repo_martin_5, String> col_adm_id;
    @FXML
    private TableColumn<repo_martin_5, String> col_adm_fname;
    @FXML
    private TableColumn<repo_martin_5, String> col_adm_lname;


    ObservableList<repo_martin_5> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_martin_5()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_martin_5(rs.getString("Teach_ID"), rs.getString("Teach_FName"), rs.getString("Teach_LName"), rs.getString("Teach_Verify"),
                        rs.getString("Adm_ID"),rs.getString("Adm_FName"),rs.getString("Adm_LName")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_martin_5.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_teach_fname.setCellValueFactory(new PropertyValueFactory<>("Teach_FName"));
        col_teach_lname.setCellValueFactory(new PropertyValueFactory<>("Teach_LName"));
        col_teach_verify.setCellValueFactory(new PropertyValueFactory<>("Teach_Verify"));
        col_adm_id.setCellValueFactory(new PropertyValueFactory<>("Adm_ID"));
        col_adm_fname.setCellValueFactory(new PropertyValueFactory<>("Adm_FName"));
        col_adm_lname.setCellValueFactory(new PropertyValueFactory<>("Adm_LName"));



        table.setItems(oblist);


    }
}