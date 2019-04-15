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

public class TransactionParHistoryController implements Initializable {

    @FXML
    private TableView<TransactionParHistory> table;
    @FXML
    private TableColumn<TransactionParHistory,String> col_trans_par_hist_id;
    @FXML
    private TableColumn<TransactionParHistory, String> col_trans_par_id;
    @FXML
    private TableColumn<TransactionParHistory, String> col_trans_date;

    @FXML
    private TextField trans_par_hist_id;
    @FXML
    private TextField trans_par_id;
    @FXML
    private TextField trans_date;


    ObservableList<TransactionParHistory> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL trans_par_hist_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new TransactionParHistory(rs.getString("Trans_Par_Hist_ID"),rs.getString("Trans_Par_ID"), rs.getString("Trans_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TransactionParHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_trans_par_hist_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Par_Hist_ID"));
        col_trans_par_id.setCellValueFactory(new PropertyValueFactory<>("Par_ID"));
        col_trans_date.setCellValueFactory(new PropertyValueFactory<>("Trans_Date"));


        table.setItems(oblist);

    }

    @FXML
    private void add_TransactionParHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_par_hist_insert(?,?)}");

            stmt.setString(1, this.trans_par_id.getText());
            stmt.setString(2, this.trans_date.getText());

            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TransactionParHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_TransactionParHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL par_transaction_delete(?)}");

            stmt.setString(1, this.trans_par_hist_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TransactionParHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_TransactionParHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_hist_par_update(?,?,?)}");

            stmt.setString(1, this.trans_par_hist_id.getText());
            stmt.setString(2, this.trans_par_id.getText());
            stmt.setString(3, this.trans_date.getText());
            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TransactionParHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
