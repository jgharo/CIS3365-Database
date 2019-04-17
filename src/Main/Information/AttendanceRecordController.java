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
import javafx.scene.control.Button;
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

public class AttendanceRecordController implements Initializable {
    @FXML
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;

    @FXML
    public TableView<Attendance_Record> attendanceTable;
    public TableColumn<Attendance_Record, String> attendCol;
    public TableColumn<Attendance_Record, String> classCol;
    public TableColumn<Attendance_Record, String> dateCol;

    @FXML
    public TextField attendanceIDText;
    public TextField classText;
    public TextField dateText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Attendance_Record> observableList = FXCollections.observableArrayList();
        attendanceTable.setItems(observableList);
        attendanceTable.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            Attendance_Record attendance_record = newval;
            if (attendance_record != null) {
                attendanceIDText.setText(attendance_record.getAtt_ID());
                classText.setText(attendance_record.getClass_ID());
                dateText.setText(attendance_record.getAtt_Date());

                attendanceTable.refresh();
            }
        });
        attendanceTable.getSelectionModel().clearSelection();

        setAttendanceTable();
        displayDatabase();

    }

    private void displayDatabase() {
        ObservableList<Attendance_Record> attendInfo = FXCollections.observableArrayList();

        try {

            Connection con = DBconnect.getConnection();

            CallableStatement stmtAttend = con.prepareCall("{CALL attendance_record_select()}");

            ResultSet rs = stmtAttend.executeQuery();
            while (rs.next()) {
                attendInfo.add(new Attendance_Record(rs.getString("Att_ID"), rs.getString("Class_ID"), rs.getString("Att_Date")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Attendance_Record.class.getName()).log(Level.SEVERE, null, ex);

        }
        attendanceTable.setItems(attendInfo);
    }

    private void setAttendanceTable() {
        attendCol.setCellValueFactory(new PropertyValueFactory<>("Att_ID"));
        classCol.setCellValueFactory(new PropertyValueFactory<>("Class_ID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Att_Date"));
    }

    public void addAttendance(ActionEvent actionEvent) {

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL attendance_record_insert(?,?)}");

            stmt.setString(1, classText.getText());
            stmt.setString(2, dateText.getText());

            stmt.execute();
            setAttendanceTable();
            displayDatabase();
            attendanceTable.getSelectionModel().clearSelection();
            attendanceTable.refresh();
            con.close();

        } catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            classText.setText("");
            dateText.setText("");
        }
    }

    public void deleteAttendance(ActionEvent actionEvent) {

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL attendance_record_delete(?)}");

            stmt.setString(1, attendanceIDText.getText());

            stmt.execute();
            setAttendanceTable();
            displayDatabase();
            con.close();
        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            classText.setText("");
        }

    }

    public void updateAttendance(ActionEvent actionEvent) {

        try {
            Connection con = DBconnect.getConnection();

            CallableStatement stmt = con.prepareCall("{CALL attendance_record_update(?,?,?)}");

            stmt.setString(1, attendanceIDText.getText());
            stmt.setString(2, classText.getText());
            stmt.setString(3, dateText.getText());

            stmt.executeUpdate();
            setAttendanceTable();
            displayDatabase();
            attendanceTable.getSelectionModel().clearSelection();
            attendanceTable.refresh();
            con.close();

        }
        catch (SQLException ex){
            Logger.getLogger(AdminAccountController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            attendanceIDText.setText("");
            classText.setText("");
            dateText.setText("");
        }

    }

    public void clearAttendance (ActionEvent actionEvent) {
       attendanceIDText.setText("");
        classText.setText("");
        dateText.setText("");
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
