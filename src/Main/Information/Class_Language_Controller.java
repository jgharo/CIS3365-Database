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


    ObservableList<Class_Language> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL class_language_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new Class_Language(rs.getString("ClassLang_ID"),rs.getString("Class_ID"),rs.getString("Class_Lang")));

            }

        } catch (SQLException ex){
            Logger.getLogger(Class_Language_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_classlang_id.setCellValueFactory(new PropertyValueFactory<>("ClassLang_ID"));
        col_class_id.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        col_class_lang.setCellValueFactory(new PropertyValueFactory<>("Class_Lang"));

        table.setItems(oblist);

    }

    @FXML
    private void add_Class_Language(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_language_insert(?,?)}");

            stmt.setString(1, this.class_id.getText());
            stmt.setString(2, this.class_lang.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(Class_Language_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_Class_Language(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_language_delete(?)}");

            stmt.setString(1, this.classlang_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(Class_Language_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_Class_Language(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL class_language_update(?,?,?)}");

            stmt.setString(1, this.classlang_id.getText());
            stmt.setString(2, this.class_id.getText());
            stmt.setString(3, this.class_lang.getText());


            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(Class_Language_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
