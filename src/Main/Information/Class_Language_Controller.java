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

public class Class_Language_Controller implements Initializable {

    @FXML
    private TableView<Class_Language> table;
    @FXML
    private TableColumn<Class_Language,String> col_classlang_id;
    @FXML
    private TableColumn<Class_Language, String> col_class_id;
    @FXML
    private TableColumn<Class_Language, String> col_class_lang;



    @FXML
    private TextField classlang_id;
    @FXML
    private TextField class_id;
    @FXML
    private TextField class_lang;

    Scene returnScene;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ObservableList<Class_Language> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            Class_Language classLanguage = newval;
            if (classLanguage != null) {
                classlang_id.setText(classLanguage.getClassLang_ID());
                class_id.setText(classLanguage.getClass_ID());
                class_lang.setText(classLanguage.getClass_Lang());
            }
        });
        table.getSelectionModel().clearSelection();

        setClassLanguage();
        displayDatabase();
    }

    private void setClassLanguage() {
        col_classlang_id.setCellValueFactory(new PropertyValueFactory<>("ClassLang_ID"));
        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_class_lang.setCellValueFactory(new PropertyValueFactory<>("Class_Lang"));
    }

    private void displayDatabase() {
        ObservableList<Class_Language> classInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAdmin = con.prepareCall("{CALL class_language_select()}");

            ResultSet rs = stmtAdmin.executeQuery();
            while (rs.next()) {
                classInfo.add(new Class_Language(rs.getString("classLang_ID"), rs.getString("class_ID"), rs.getString("class_Lang")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);

        }
        table.setItems(classInfo);
    }

    @FXML
    private void add_Class_Language(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_language_insert(?,?)}");

            stmt.setString(1, class_id.getText());
            stmt.setString(2, class_lang.getText());

            stmt.execute();
            setClassLanguage();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(Class_Language_Controller.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            class_id.setText("");
            class_lang.setText("");
        }

    }

    @FXML
    private void delete_Class_Language(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_language_delete(?)}");

            stmt.setString(1, classlang_id.getText());

            stmt.execute();
            setClassLanguage();
            displayDatabase();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(Class_Language_Controller.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            classlang_id.setText("");
        }

    }

    @FXML
    private void update_Class_Language(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_language_update(?,?,?)}");

            stmt.setString(1, classlang_id.getText());
            stmt.setString(2, class_id.getText());
            stmt.setString(3, class_lang.getText());

            stmt.executeUpdate();
            setClassLanguage();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex) {
            Logger.getLogger(Class_Language_Controller.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            classlang_id.setText("");
            class_id.setText("");
            class_lang.setText("");
        }


    }

    public void clearClassLang (ActionEvent actionEvent) {
        classlang_id.setText("");
        class_id.setText("");
        class_lang.setText("");
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
