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

public class repo_haro_2_controller implements Initializable {

    @FXML
    private TableView<repo_haro_2> table;
    @FXML
    private TableColumn<repo_haro_2, String> col_trans_par_id;
    @FXML
    private TableColumn<repo_haro_2, String> col_preg_id;
    @FXML
    private TableColumn<repo_haro_2, String> col_trans_remain;
    @FXML
    private TableColumn<repo_haro_2, String> col_trans_date;


    ObservableList<repo_haro_2> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_haro_2()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_haro_2(rs.getString("Trans_Par_ID"),rs.getString("PReg_ID"),rs.getString("Trans_Remain"),rs.getString("Trans_Date")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_haro_2.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_trans_par_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Par_ID"));
        col_preg_id.setCellValueFactory(new PropertyValueFactory<>("PReg_ID"));
        col_trans_remain.setCellValueFactory(new PropertyValueFactory<>("Trans_Remain"));
        col_trans_date.setCellValueFactory(new PropertyValueFactory<>("Trans_Date"));


        table.setItems(oblist);


    }
}
