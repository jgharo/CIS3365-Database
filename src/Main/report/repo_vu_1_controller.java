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

public class repo_vu_1_controller implements Initializable {

    @FXML
    private TableView<repo_vu_1> table;
    @FXML
    private TableColumn<repo_vu_1, String> col_class_id;
    @FXML
    private TableColumn<repo_vu_1, String> col_class_name;
    @FXML
    private TableColumn<repo_vu_1, String> col_class_lang;
    @FXML
    private TableColumn<repo_vu_1, String> col_class_room;
    @FXML
    private TableColumn<repo_vu_1, String> col_course_name;


    ObservableList<repo_vu_1> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_vu_1()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_vu_1(rs.getString("Class_ID"), rs.getString("Class_Name"), rs.getString("Class_Lang"), rs.getString("Class_Room"),
                        rs.getString("Course_Name")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_vu_1.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_class_name.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
        col_class_lang.setCellValueFactory(new PropertyValueFactory<>("Class_Lang"));
        col_class_room.setCellValueFactory(new PropertyValueFactory<>("Class_Room"));
        col_course_name.setCellValueFactory(new PropertyValueFactory<>("Course_Name"));



        table.setItems(oblist);


    }
}
