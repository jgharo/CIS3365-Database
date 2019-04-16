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


    Scene returnScene;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Classroom> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            Classroom classroom = newval;
            if (classroom != null) {
                classroom_id.setText(classroom.getClassRoom_ID());
                class_id.setText(classroom.getClass_ID());
                class_room.setText(classroom.getClass_Room());

            }
        });
        table.getSelectionModel().clearSelection();

        setClassroomTable();
        displayDatabase();

    }

    private void setClassroomTable() {
        col_classroom_id.setCellValueFactory(new PropertyValueFactory<>("ClassRoom_ID"));
        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_class_room.setCellValueFactory(new PropertyValueFactory<>("Class_Room"));

    }

    private void displayDatabase() {
        ObservableList<Classroom> classroomInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL classroom_select()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                classroomInfo.add(new Classroom(rs.getString("classRoom_ID"), rs.getString("class_ID"), rs.getString("class_Room")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(classroomInfo);
    }

    public void add_Classroom(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL classroom_insert(?,?)}");

            stmt.setString(1, class_id.getText());
            stmt.setString(2, class_room.getText());

            stmt.execute();
            setClassroomTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        } catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            class_id.setText("");
            class_room.setText("");
        }

    }

    public void delete_Classroom(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL classroom_delete(?)}");

            stmt.setString(1, classroom_id.getText());

            stmt.execute();
            setClassroomTable();
            displayDatabase();
            con.close();
        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            classroom_id.setText("");
            class_id.setText("");
            class_room.setText("");
        }

    }

    public void update_Classroom(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL classroom_update(?,?,?)}");

            stmt.setString(1, classroom_id.getText());
            stmt.setString(2, class_id.getText());
            stmt.setString(3, class_room.getText());

            stmt.executeUpdate();
            setClassroomTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(ClassroomController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            classroom_id.setText("");
            class_id.setText("");
            class_room.setText("");
        }

    }

    public void clearClassroom (ActionEvent actionEvent) {
        classroom_id.setText("");
        class_id.setText("");
        class_room.setText("");
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
