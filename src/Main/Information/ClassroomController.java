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

public class ClassroomController implements Initializable {

    @FXML
    private TableView<Classroom> table;
    @FXML
    private TableColumn<Classroom,String> col_classroom_id;
    @FXML
    private TableColumn<Classroom, String> col_class_id;
    @FXML
    private TableColumn<Classroom, String> col_class_room;



    @FXML
    private TextField classroom_id;
    @FXML
    private TextField class_id;
    @FXML
    private TextField class_room;


    ObservableList<Classroom> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL class_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new Classroom(rs.getString("ClassRoom_ID"),rs.getString("Class_ID"),rs.getString("Class_Room")));

            }

        } catch (SQLException ex){
            Logger.getLogger(ClassroomController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_classroom_id.setCellValueFactory(new PropertyValueFactory<>("ClassRoom_ID"));
        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_class_room.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));

        table.setItems(oblist);

    }

    @FXML
    private void add_Classroom(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_insert(?,?)}");

            stmt.setString(1, this.class_id.getText());
            stmt.setString(2, this.class_room.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(ClassroomController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_Classroom(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_delete(?)}");

            stmt.setString(1, this.classroom_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ClassroomController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_Classroom(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL course_update(?,?,?)}");

            stmt.setString(1, this.classroom_id.getText());
            stmt.setString(2, this.class_id.getText());
            stmt.setString(3, this.class_room.getText());


            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ClassroomController.class.getName()).log(Level.SEVERE, null, ex);
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
