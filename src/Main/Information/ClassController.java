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

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ObservableList<Class> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            Class classes = newval;
            if (classes != null) {
                class_id.setText(classes.getClass_ID());
                teach_id.setText(classes.getTeach_ID());
                course_id.setText(classes.getCourse_ID());
                class_name.setText(classes.getClass_Name());
            }
        });
        table.getSelectionModel().clearSelection();

        setClassTable();
        displayDatabase();
    }

    private void setClassTable() {
        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_teach_id.setCellValueFactory(new PropertyValueFactory<>("Teach_ID"));
        col_course_id.setCellValueFactory(new PropertyValueFactory<>("Course_ID"));
        col_class_name.setCellValueFactory(new PropertyValueFactory<>("Class_Name"));
    }

    private void displayDatabase() {
        ObservableList<Class> classInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL class_select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                classInfo.add(new Class(rs.getString("class_ID"), rs.getString("teach_ID"), rs.getString("course_ID"),
                        rs.getString("class_Name")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(classInfo);
    }

    @FXML
    private void add_Class(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_insert(?,?,?)}");

            stmt.setString(1, teach_id.getText());
            stmt.setString(2, course_id.getText());
            stmt.setString(3, class_name.getText());

            stmt.execute();
            setClassTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        } catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            teach_id.setText("");
            course_id.setText("");
            class_name.setText("");
        }
    }

    @FXML
    private void delete_Class(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_delete(?)}");

            stmt.setString(1, class_id.getText());

            stmt.execute();
            setClassTable();
            displayDatabase();
            con.close();
        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            class_id.setText("");
            teach_id.setText("");
            course_id.setText("");
            class_name.setText("");
        }

    }

    public void update_Class(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_update(?,?,?,?)}");

            stmt.setString(1, class_id.getText());
            stmt.setString(2, teach_id.getText());
            stmt.setString(3, course_id.getText());
            stmt.setString(4, class_name.getText());

            stmt.executeUpdate();
            setClassTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            class_id.setText("");
            teach_id.setText("");
            course_id.setText("");
            class_name.setText("");
        }

    }

    public void clearClass (ActionEvent actionEvent) {
        class_id.setText("");
        teach_id.setText("");
        course_id.setText("");
        class_name.setText("");
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
