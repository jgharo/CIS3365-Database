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

public class repo_haro_5_controller implements Initializable {

    @FXML
    private TableView<repo_haro_5> table;
    @FXML
    private TableColumn<repo_haro_5, String> col_class_id;
    @FXML
    private TableColumn<repo_haro_5, String> col_class_name;
    @FXML
    private TableColumn<repo_haro_5, String> col_class_lang;
    @FXML
    private TableColumn<repo_haro_5, String> col_teach_fname;
    @FXML
    private TableColumn<repo_haro_5, String> col_teach_lname;


    ObservableList<repo_haro_5> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_haro_5()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_haro_5(rs.getString("Class_ID"),rs.getString("Class_Name"), rs.getString("Class_Lang"), rs.getString("Teach_FName"),
                        rs.getString("Teach_LName")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_haro_5.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_class_name.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
        col_class_lang.setCellValueFactory(new PropertyValueFactory<>("Class_Lang"));
        col_teach_fname.setCellValueFactory(new PropertyValueFactory<>("Teach_FName"));
        col_teach_lname.setCellValueFactory(new PropertyValueFactory<>("Teach_LName"));


        table.setItems(oblist);


    }
}
