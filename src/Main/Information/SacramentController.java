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

public class SacramentController implements Initializable {

    @FXML
    private TableView<Sacrament> table;
    @FXML
    private TableColumn<Sacrament,String> col_sac_id;
    @FXML
    private TableColumn<Sacrament, String> col_sac_type;

    @FXML
    private TextField sac_id;
    @FXML
    private TextField sac_type;


    ObservableList<Sacrament> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu= con.prepareCall("{CALL sacrament_select()}");

            ResultSet rs= stmtstu.executeQuery();
            while (rs.next()){
                oblist.add(new Sacrament(rs.getString("Sac_ID"),rs.getString("Sac_Type")));

            }

        } catch (SQLException ex){
            Logger.getLogger(SacramentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_sac_id.setCellValueFactory(new PropertyValueFactory<>("Sac_ID"));
        col_sac_type.setCellValueFactory(new PropertyValueFactory<>("Sac_Type"));

        table.setItems(oblist);

    }

    @FXML
    private void add_Sacrament(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL sacrament_insert(?)}");

            stmt.setString(1, this.sac_type.getText());
            stmt.execute();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(SacramentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_Sacrament(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmtstu = con.prepareCall("{CALL sacrament_delete(?)}");

            stmtstu.setString(1, this.sac_id.getText());

            stmtstu.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(SacramentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_Sacrament(ActionEvent event){

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL sacrament_update(?,?)}");

            stmt.setString(1, this.sac_id.getText());
            stmt.setString(2, this.sac_type.getText());


            stmt.execute();
            con.close();


        }
        catch (SQLException ex){
            Logger.getLogger(SacramentController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
