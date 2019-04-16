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

public class StudentTransactionFormController implements Initializable {

    @FXML
    private TableView<StudentTransactionForm> table;
    @FXML
    private TableColumn<StudentTransactionForm,String> col_trans_stu_id;
    @FXML
    private TableColumn<StudentTransactionForm, String> col_sreg_id;
    @FXML
    private TableColumn<StudentTransactionForm, String> col_trans_amt;
    @FXML
    private TableColumn<StudentTransactionForm, String> col_trans_remain;
    @FXML
    private TableColumn<StudentTransactionForm, String> col_due_date;

    @FXML
    private TextField trans_stu_id;
    @FXML
    private TextField sreg_id;
    @FXML
    private TextField trans_amt;
    @FXML
    private TextField trans_remain;
    @FXML
    private TextField due_date;


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<StudentTransactionForm> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            StudentTransactionForm stu = newval;
            if (stu != null) {
                trans_stu_id.setText(stu.getTrans_Stu_ID());
                sreg_id.setText(stu.getSReg_ID());
                trans_amt.setText(stu.getTrans_Amt());
                trans_remain.setText(stu.getTrans_Remain());
                due_date.setText(stu.getDue_Date());

            }
        });
        table.getSelectionModel().clearSelection();

        setStudentTransTable();
        displayDatabase();

    }

    private void setStudentTransTable(){

        col_trans_stu_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Stu_ID"));
        col_sreg_id.setCellValueFactory(new PropertyValueFactory<>("SReg_ID"));
        col_trans_amt.setCellValueFactory(new PropertyValueFactory<>("Trans_Amt"));
        col_trans_remain.setCellValueFactory(new PropertyValueFactory<>("Trans_Remain"));
        col_due_date.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
    }

    private void displayDatabase(){


        ObservableList<StudentTransactionForm> studenttransInfo = FXCollections.observableArrayList();

        try{

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL stu_transaction_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                studenttransInfo.add(new StudentTransactionForm(rs.getString("Trans_Stu_ID"),rs.getString("SReg_ID"), rs.getString("Trans_Amt"), rs.getString("Trans_Remain"),
                        rs.getString("Due_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(StudentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }



        table.setItems(studenttransInfo);

    }




    @FXML
    private void add_StudentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL stu_transaction_insert(?,?,?,?)}");

            stmt.setString(1, this.sreg_id.getText());
            stmt.setString(2, this.trans_amt.getText());
            stmt.setString(3, this.trans_remain.getText());
            stmt.setString(4, this.due_date.getText());
            stmt.execute();
            setStudentTransTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(StudentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            trans_stu_id.setText("");
            sreg_id.setText("");
            trans_amt.setText("");
            trans_remain.setText("");
            due_date.setText("");
        }

    }

    @FXML
    private void delete_StudentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL stu_transaction_delete(?)}");

            stmt.setString(1, this.trans_stu_id.getText());

            stmt.execute();
            setStudentTransTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{

            trans_stu_id.setText("");
            sreg_id.setText("");
            trans_amt.setText("");
            trans_remain.setText("");
            due_date.setText("");
        }

    }

    @FXML
    private void update_StudentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL stu_transaction_update(?,?,?,?,?)}");

            stmt.setString(1, this.trans_stu_id.getText());
            stmt.setString(2, this.sreg_id.getText());
            stmt.setString(3, this.trans_amt.getText());
            stmt.setString(4, this.trans_remain.getText());
            stmt.setString(5, this.due_date.getText());

            stmt.execute();
            setStudentTransTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(StudentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{

            trans_stu_id.setText("");
            sreg_id.setText("");
            trans_amt.setText("");
            trans_remain.setText("");
            due_date.setText("");
        }


    }
    @FXML
    private void clear_StudentTransactionForm(ActionEvent actionEvent) {
        trans_stu_id.setText("");
        sreg_id.setText("");
        trans_amt.setText("");
        trans_remain.setText("");
        due_date.setText("");
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
