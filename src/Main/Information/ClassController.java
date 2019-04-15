package Information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassController implements Initializable {

    @FXML
    private TableView<Class> table;
    @FXML
    private TableColumn<Class,String> col_class_id;
    @FXML
    private TableColumn<Class, String> col_teach_id;
    @FXML
    private TableColumn<Class, String> col_course_id;
    @FXML
    private TableColumn<Class, String> col_class_name;


    @FXML
    private TextField class_id;
    @FXML
    private TextField teach_id;
    @FXML
    private TextField course_id;
    @FXML
    private TextField class_name;


    ObservableList<Class> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL class_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new Class(rs.getString("Class_ID"),rs.getString("Teach_ID"),rs.getString("Course_ID"),rs.getString("Class_Name")));

            }

        } catch (SQLException ex){
            Logger.getLogger(ClassController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_course_id.setCellValueFactory(new PropertyValueFactory<>("Course_ID"));
        col_class_name.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));

        table.setItems(oblist);

    }

    @FXML
    private void add_Class(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_insert(?,?,?)}");

            stmt.setString(1, this.teach_id.getText());
            stmt.setString(2, this.course_id.getText());
            stmt.setString(3, this.class_name.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(ClassController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_Class(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_delete(?)}");

            stmt.setString(1, this.class_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ClassController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_Class(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL course_update(?,?,?,?)}");

            stmt.setString(1, this.class_id.getText());
            stmt.setString(2, this.teach_id.getText());
            stmt.setString(3, this.course_id.getText());
            stmt.setString(4, this.class_id.getText());


            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ClassController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
