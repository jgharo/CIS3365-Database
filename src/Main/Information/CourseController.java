package Information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseController implements Initializable {

    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<Course,String> col_course_id;
    @FXML
    private TableColumn<Course, String> col_course_sac;
    @FXML
    private TableColumn<Course, String> col_course_name;

    @FXML
    private TextField course_id;
    @FXML
    private TextField course_sac;
    @FXML
    private TextField course_name;

    Scene returnScene;

    ObservableList<Course> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL course_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new Course(rs.getString("Course_ID"),rs.getString("Course_Sac"),rs.getString("Course_Name")));

            }

        } catch (SQLException ex){
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_course_id.setCellValueFactory(new PropertyValueFactory<>("Course_ID"));
        col_course_sac.setCellValueFactory(new PropertyValueFactory<>("Course_Sac"));
        col_course_name.setCellValueFactory(new PropertyValueFactory<>("Course_Name"));

        table.setItems(oblist);

    }

    @FXML
    private void add_Course(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL course_insert(?,?)}");

            stmt.setString(1, this.course_sac.getText());
            stmt.setString(2, this.course_name.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_Course(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL course_delete(?)}");

            stmt.setString(1, this.course_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_Course(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL course_update(?,?,?)}");

            stmt.setString(1, this.course_id.getText());
            stmt.setString(2, this.course_sac.getText());
            stmt.setString(3, this.course_name.getText());


            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("Duplicates")
    public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("tableSelection.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Table Selection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
