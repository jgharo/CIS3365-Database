package Information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class AttendanceRecordController implements Initializable {
    @FXML
    public Button menuButton;
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;

    @FXML
    public TableView attendanceTable;
    public TableColumn attendCol;
    public TableColumn classCol;
    public TableColumn dateCol;

    @FXML
    public TextField attendanceIDText;
    public TextField classText;
    public TextField dateText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Attendance_Record> observableList = FXCollections.observableArrayList();
        attendanceTable.setItems(observableList);
        attendanceTable.getSelectionModel().selectedItemProperty().addListener((observable,oldval,newval) -> {
            Attendance_Record attendance_record = (Attendance_Record) newval;
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
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Att_date"));
    }

    public void addAttendance(ActionEvent actionEvent) {

    }

    public void deleteAttendance(ActionEvent actionEvent) {

    }

    public void updateAttendance(ActionEvent actionEvent) {

    }


    public void mainmenu(ActionEvent actionEvent) {

    }


}
