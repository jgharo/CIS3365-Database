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

public class TransactionStuHistoryController implements Initializable {

    @FXML
    private TableView<TransactionStuHistory> table;
    @FXML
    private TableColumn<TransactionStuHistory,String> col_trans_stu_hist_id;
    @FXML
    private TableColumn<TransactionStuHistory, String> col_trans_stu_id;
    @FXML
    private TableColumn<TransactionStuHistory, String> col_trans_date;

    @FXML
    private TextField trans_stu_hist_id;
    @FXML
    private TextField trans_stu_id;
    @FXML
    private TextField trans_date;


    ObservableList<TransactionStuHistory> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL trans_stu_hist_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                oblist.add(new TransactionStuHistory(rs.getString("Trans_Stu_Hist_ID"),rs.getString("Trans_Stu_ID"), rs.getString("Trans_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TransactionStuHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_trans_stu_hist_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Stu_Hist_ID"));
        col_trans_stu_id.setCellValueFactory(new PropertyValueFactory<>("Stu_ID"));
        col_trans_date.setCellValueFactory(new PropertyValueFactory<>("Trans_Date"));


        table.setItems(oblist);

    }

    @FXML
    private void add_TransactionStuHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_stu_hist_insert(?,?)}");

            stmt.setString(1, this.trans_stu_id.getText());
            stmt.setString(2, this.trans_date.getText());

            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TransactionStuHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_TransactionStuHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL stu_transaction_delete(?)}");

            stmt.setString(1, this.trans_stu_hist_id.getText());

            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TransactionStuHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_TransactionStuHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_hist_stu_update(?,?,?)}");

            stmt.setString(1, this.trans_stu_hist_id.getText());
            stmt.setString(2, this.trans_stu_id.getText());
            stmt.setString(3, this.trans_date.getText());
            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TransactionStuHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
