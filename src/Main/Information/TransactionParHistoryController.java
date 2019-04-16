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

public class TransactionParHistoryController implements Initializable {

    @FXML
    private TableView<TransactionParHistory> table;
    @FXML
    private TableColumn<TransactionParHistory, String> col_trans_par_hist_id;
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

    Scene returnScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<TransactionParHistory> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldval, newval) -> {
            TransactionParHistory admin = newval;
            if (admin != null) {
                trans_par_hist_id.setText(admin.getTrans_Par_Hist_ID());
                trans_par_id.setText(admin.getTrans_Par_ID());
                trans_date.setText(admin.getTrans_Date());
            }
        });
        table.getSelectionModel().clearSelection();

        setTransactionParHistoryTable();
        displayDatabase();
    }

    private void setTransactionParHistoryTable() {

        col_trans_par_hist_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Par_Hist_ID"));
        col_trans_par_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Par_ID"));
        col_trans_date.setCellValueFactory(new PropertyValueFactory<>("Trans_Date"));
}


    private void displayDatabase(){

        ObservableList<TransactionParHistory> transparhistInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL trans_par_hist_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                transparhistInfo.add(new TransactionParHistory(rs.getString("Trans_Par_Hist_ID"),rs.getString("Trans_Par_ID"), rs.getString("Trans_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TransactionParHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(transparhistInfo);

    }

    @FXML
    private void add_TransactionParHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_par_hist_insert(?,?)}");

            stmt.setString(1, this.trans_par_id.getText());
            stmt.setString(2, this.trans_date.getText());

            stmt.execute();
            setTransactionParHistoryTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TransactionParHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            trans_par_hist_id.setText("");
            trans_par_id.setText("");
            trans_date.setText("");
        }

    }

    @FXML
    private void delete_TransactionParHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_par_hist_delete(?)}");

            stmt.setString(1, this.trans_par_hist_id.getText());

            stmt.execute();
            setTransactionParHistoryTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TransactionParHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            trans_par_hist_id.setText("");

        }

    }

    @FXML
    private void update_TransactionParHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_par_hist_update(?,?,?)}");

            stmt.setString(1, this.trans_par_hist_id.getText());
            stmt.setString(2, this.trans_par_id.getText());
            stmt.setString(3, this.trans_date.getText());
            stmt.execute();
            setTransactionParHistoryTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TransactionParHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            trans_par_hist_id.setText("");
            trans_par_id.setText("");
            trans_date.setText("");
        }

    }

    public void clearTransactionParHistory (ActionEvent actionEvent) {
        trans_par_hist_id.setText("");
        trans_par_id.setText("");
        trans_date.setText("");
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
