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

public class AttCheckController implements Initializable {

    @FXML
    private TableView<AttCheck> table;
    @FXML
    private TableColumn<AttCheck, String> col_att_id;
    @FXML
    private TableColumn<AttCheck, String> col_stu_id;
    @FXML
    private TableColumn<AttCheck, String> col_att_stat;

    @FXML
    private TextField att_id;
    @FXML
    private TextField stu_id;
    @FXML
    private TextField att_stat;


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<AttCheck> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            AttCheck stu = newval;
            if (stu != null) {
                att_id.setText(stu.getAtt_ID());
                stu_id.setText(stu.getStu_ID());
                att_stat.setText(stu.getAtt_Stat());


            }
        });
        table.getSelectionModel().clearSelection();

        setAttCheckTable();
        displayDatabase();

    }

    private void setAttCheckTable() {
        col_att_id.setCellValueFactory(new PropertyValueFactory<>("Att_ID"));
        col_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_att_stat.setCellValueFactory(new PropertyValueFactory<>("Att_Stat"));
    }


    private void displayDatabase(){

        ObservableList<AttCheck> attcheckInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL att_check_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                attcheckInfo.add(new AttCheck(rs.getString("Att_ID"),rs.getString("Stu_ID"), rs.getString("Att_Stat")));

            }

        } catch (SQLException ex){
            Logger.getLogger(AttCheckController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(attcheckInfo);

    }

    @FXML
    private void add_AttCheck(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL att_check_insert(?,?,?)}");

            stmt.setString(1, this.att_id.getText());
            stmt.setString(2, this.stu_id.getText());
            stmt.setString(3, this.att_stat.getText());
            stmt.execute();
            setAttCheckTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TeacherEnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            att_id.setText("");
            stu_id.setText("");
            att_stat.setText("");
        }

    }

    @FXML
    private void delete_AttCheck(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL att_check_delete(?,?)}");

            stmtstu.setString(1, this.att_id.getText());
            stmtstu.setString(2, this.stu_id.getText());

            stmtstu.execute();
            setAttCheckTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(AttCheckController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            att_id.setText("");
            stu_id.setText("");
            att_stat.setText("");
        }

    }

    @FXML
    private void update_AttCheck(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL att_check_update(?,?,?)}");

            stmt.setString(1, this.att_id.getText());
            stmt.setString(2, this.stu_id.getText());
            stmt.setString(3, this.att_stat.getText());

            stmt.execute();
            setAttCheckTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(AttCheckController.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally{
            att_id.setText("");
            stu_id.setText("");
            att_stat.setText("");
        }

    }

    @FXML
    private void clear_AttCheck(ActionEvent actionEvent) {
        att_id.setText("");
        stu_id.setText("");
        att_stat.setText("");
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
