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

public class repo_evans_2_controller implements Initializable {

    @FXML
    private TableView<repo_evans_2> table;
    @FXML
    private TableColumn<repo_evans_2, String> col_teach_id;
    @FXML
    private TableColumn<repo_evans_2, String> col_teach_fname;
    @FXML
    private TableColumn<repo_evans_2, String> col_teach_lname;
    @FXML
    private TableColumn<repo_evans_2, String> col_teach_address;
    @FXML
    private TableColumn<repo_evans_2, String> col_teach_email;
    @FXML
    private TableColumn<repo_evans_2, String> col_class_name;
    @FXML
    private TableColumn<repo_evans_2, String> col_class_room;
    @FXML
    private TableColumn<repo_evans_2, String> col_course_name;


    ObservableList<repo_evans_2> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_evans_2()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_evans_2(rs.getString("Teach_ID"), rs.getString("Teach_FName"), rs.getString("Teach_LName"), rs.getString("Teach_Address"),
                        rs.getString("Teach_Email"),rs.getString("Class_Name"),rs.getString("Class_Room"), rs.getString("Course_Name")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_evans_2.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_teach_fname.setCellValueFactory(new PropertyValueFactory<>("Teach_FName"));
        col_teach_lname.setCellValueFactory(new PropertyValueFactory<>("Teach_LName"));
        col_teach_address.setCellValueFactory(new PropertyValueFactory<>("Teach_Address"));
        col_teach_email.setCellValueFactory(new PropertyValueFactory<>("Teach_Email"));
        col_class_name.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
        col_class_room.setCellValueFactory(new PropertyValueFactory<>("Class_Room"));
        col_course_name.setCellValueFactory(new PropertyValueFactory<>("Course_Name"));




        table.setItems(oblist);


    }
}
