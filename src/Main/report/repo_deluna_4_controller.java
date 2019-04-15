package report;
import Information.DBconnect;
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

public class repo_deluna_4_controller implements Initializable {

    @FXML
    private TableView<repo_deluna_4> table;
    @FXML
    private TableColumn<repo_deluna_4, String> col_preg_id;
    @FXML
    private TableColumn<repo_deluna_4, String> col_trans_par_id;
    @FXML
    private TableColumn<repo_deluna_4, String> col_trans_amt;
    @FXML
    private TableColumn<repo_deluna_4, String> col_trans_date;


    ObservableList<repo_deluna_4> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL repo_deluna_4()}");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oblist.add(new repo_deluna_4(rs.getString("PReg_ID"), rs.getString("Trans_Par_ID"),rs.getString("Trans_Amt"), rs.getString("Trans_Date")));


            }

        } catch (SQLException ex) {
            Logger.getLogger(repo_deluna_4.class.getName()).log(Level.SEVERE, null, ex);
        }


        col_preg_id.setCellValueFactory(new PropertyValueFactory<>("PReg_ID"));
        col_trans_par_id.setCellValueFactory(new PropertyValueFactory<>("Trans_Par_ID"));
        col_trans_amt.setCellValueFactory(new PropertyValueFactory<>("Trans_Amt"));
        col_trans_date.setCellValueFactory(new PropertyValueFactory<>("Trans_Date"));

        table.setItems(oblist);


    }

    @SuppressWarnings("Duplicates")
    public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("reportSelection.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Report Selection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
