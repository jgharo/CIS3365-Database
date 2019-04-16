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

public class ParentTransactionFormController implements Initializable {

    @FXML
    private TableView<ParentTransactionForm> table;
    @FXML
    private TableColumn<ParentTransactionForm,String> col_trans_par_id;
    @FXML
    private TableColumn<ParentTransactionForm, String> col_preg_id;
    @FXML
    private TableColumn<ParentTransactionForm, String> col_trans_amt;
    @FXML
    private TableColumn<ParentTransactionForm, String> col_trans_remain;
    @FXML
    private TableColumn<ParentTransactionForm, String> col_due_date;

    @FXML
    private TextField trans_par_id;
    @FXML
    private TextField preg_id;
    @FXML
    private TextField trans_amt;
    @FXML
    private TextField trans_remain;
    @FXML
    private TextField due_date;


    Scene returnScene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ParentTransactionForm> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            ParentTransactionForm stu = newval;
            if (stu != null) {
                trans_par_id.setText(stu.getTrans_Par_ID());
                preg_id.setText(stu.getPReg_ID());
                trans_amt.setText(stu.getTrans_Amt());
                trans_remain.setText(stu.getTrans_Remain());
                due_date.setText(stu.getDue_Date());

            }
        });
        table.getSelectionModel().clearSelection();

        setParentTransTable();
        displayDatabase();

    }

    private void setParentTransTable(){

        col_trans_par_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Par_ID"));
        col_preg_id.setCellValueFactory(new PropertyValueFactory<>("PReg_ID"));
        col_trans_amt.setCellValueFactory(new PropertyValueFactory<>("Trans_Amt"));
        col_trans_remain.setCellValueFactory(new PropertyValueFactory<>("Trans_Remain"));
        col_due_date.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
    }

    private void displayDatabase(){


            ObservableList<ParentTransactionForm> parenttransInfo = FXCollections.observableArrayList();

            try{

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL par_transaction_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                parenttransInfo.add(new ParentTransactionForm(rs.getString("Trans_Par_ID"),rs.getString("PReg_ID"), rs.getString("Trans_Amt"), rs.getString("Trans_Remain"),
                        rs.getString("Due_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }



        table.setItems(parenttransInfo);

    }




    @FXML
    private void add_ParentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL par_transaction_insert(?,?,?,?)}");

            stmt.setString(1, this.preg_id.getText());
            stmt.setString(2, this.trans_amt.getText());
            stmt.setString(3, this.trans_remain.getText());
            stmt.setString(4, this.due_date.getText());
            stmt.execute();
            setParentTransTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            trans_par_id.setText("");
            preg_id.setText("");
            trans_amt.setText("");
            trans_remain.setText("");
            due_date.setText("");
        }

    }

    @FXML
    private void delete_ParentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL par_transaction_delete(?)}");

            stmt.setString(1, this.trans_par_id.getText());

            stmt.execute();
            setParentTransTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{

            trans_par_id.setText("");
            preg_id.setText("");
            trans_amt.setText("");
            trans_remain.setText("");
            due_date.setText("");
        }

    }

    @FXML
    private void update_ParentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL par_transaction_update(?,?,?,?,?)}");

            stmt.setString(1, this.trans_par_id.getText());
            stmt.setString(2, this.preg_id.getText());
            stmt.setString(3, this.trans_amt.getText());
            stmt.setString(4, this.trans_remain.getText());
            stmt.setString(5, this.due_date.getText());

            stmt.execute();
            setParentTransTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{

            trans_par_id.setText("");
            preg_id.setText("");
            trans_amt.setText("");
            trans_remain.setText("");
            due_date.setText("");
        }


    }
    @FXML
    private void clear_ParentTransactionForm(ActionEvent actionEvent) {
        trans_par_id.setText("");
        preg_id.setText("");
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
