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

public class TransactionStuHistoryController implements Initializable {

    @FXML
    private TableView<TransactionStuHistory> table;
    @FXML
    private TableColumn<TransactionStuHistory, String> col_trans_stu_hist_id;
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

    Scene returnScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<TransactionStuHistory> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldval, newval) -> {
            TransactionStuHistory admin = newval;
            if (admin != null) {
                trans_stu_hist_id.setText(admin.getTrans_Stu_Hist_ID());
                trans_stu_id.setText(admin.getTrans_Stu_ID());
                trans_date.setText(admin.getTrans_Date());
            }
        });
        table.getSelectionModel().clearSelection();

        setTransactionStuHistoryTable();
        displayDatabase();
    }

    private void setTransactionStuHistoryTable() {

        col_trans_stu_hist_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Stu_Hist_ID"));
        col_trans_stu_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Stu_ID"));
        col_trans_date.setCellValueFactory(new PropertyValueFactory<>("Trans_Date"));
    }


    private void displayDatabase(){

        ObservableList<TransactionStuHistory> transstuhistInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt= con.prepareCall("{CALL trans_stu_hist_select()}");

            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                transstuhistInfo.add(new TransactionStuHistory(rs.getString("Trans_Stu_Hist_ID"),rs.getString("Trans_Stu_ID"), rs.getString("Trans_Date")));

            }

        } catch (SQLException ex){
            Logger.getLogger(TransactionStuHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setItems(transstuhistInfo);

    }

    @FXML
    private void add_TransactionStuHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_stu_hist_insert(?,?)}");

            stmt.setString(1, this.trans_stu_id.getText());
            stmt.setString(2, this.trans_date.getText());

            stmt.execute();
            setTransactionStuHistoryTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(TransactionStuHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            trans_stu_hist_id.setText("");
            trans_stu_id.setText("");
            trans_date.setText("");
        }

    }

    @FXML
    private void delete_TransactionStuHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_stu_hist_delete(?)}");

            stmt.setString(1, this.trans_stu_hist_id.getText());

            stmt.execute();
            setTransactionStuHistoryTable();
            displayDatabase();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TransactionStuHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            trans_stu_hist_id.setText("");

        }

    }

    @FXML
    private void update_TransactionStuHistory(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL trans_stu_hist_update(?,?,?)}");

            stmt.setString(1, this.trans_stu_hist_id.getText());
            stmt.setString(2, this.trans_stu_id.getText());
            stmt.setString(3, this.trans_date.getText());
            stmt.execute();
            setTransactionStuHistoryTable();
            displayDatabase();
            table.getSelectionModel().clearSelection();
            table.refresh();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(TransactionStuHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            trans_stu_hist_id.setText("");
            trans_stu_id.setText("");
            trans_date.setText("");
        }

    }

    public void clearTransactionStuHistory (ActionEvent actionEvent) {
        trans_stu_hist_id.setText("");
        trans_stu_id.setText("");
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
