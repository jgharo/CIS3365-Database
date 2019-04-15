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

public class ParentTransactionFormController implements Initializable {

    @FXML
    private TableView<ParentTransactionForm> table;
    @FXML
    private TableColumn<ParentTransactionForm,String> col_trans_par_id;
    @FXML
    private TableColumn<ParentTransactionForm, String> col_par_id;
    @FXML
    private TableColumn<ParentTransactionForm, String> col_trans_amt;
    @FXML
    private TableColumn<ParentTransactionForm, String> col_trans_remain;
    @FXML
    private TableColumn<ParentTransactionForm, String> col_due_date;

    @FXML
    private TextField trans_par_id;
    @FXML
    private TextField par_id;
    @FXML
    private TextField trans_amt;
    @FXML
    private TextField trans_remain;
    @FXML
    private TextField due_date;


    ObservableList<ParentTransactionForm> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL par_transaction_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new ParentTransactionForm(rs.getString("Trans_Par_ID"),rs.getString("Par_ID"), rs.getString("Trans_Amt"), rs.getString("Trans_Remain"),
                        rs.getString("Due_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_trans_par_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Par_ID"));
        col_par_id.setCellValueFactory(new PropertyValueFactory<>("Par_ID"));
        col_trans_amt.setCellValueFactory(new PropertyValueFactory<>("Trans_Amt"));
        col_trans_remain.setCellValueFactory(new PropertyValueFactory<>("Trans_Remain"));
        col_due_date.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));

        table.setItems(oblist);

    }

    @FXML
    private void add_ParentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL par_transaction_insert(?,?,?,?)}");

            stmt.setString(1, this.par_id.getText());
            stmt.setString(2, this.trans_amt.getText());
            stmt.setString(3, this.trans_remain.getText());
            stmt.setString(4, this.due_date.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_ParentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL par_transaction_delete(?)}");

            stmt.setString(1, this.trans_par_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_ParentTransactionForm(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL par_transaction_update(?,?,?,?,?)}");

            stmt.setString(1, this.trans_par_id.getText());
            stmt.setString(2, this.par_id.getText());
            stmt.setString(3, this.trans_amt.getText());
            stmt.setString(4, this.trans_remain.getText());
            stmt.setString(5, this.due_date.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(ParentTransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
