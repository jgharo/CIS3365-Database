package report;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportSelectionController implements Initializable
{
    @FXML public Button backButton;
    @FXML public Button submitButton;
    @FXML ComboBox<String> reportSelectionBox;

    private String[] reports = new String[]{"Stu RegVerEnr w/ Class", "Stu Sac w/ Course", "Teach RegVer w/ RegDate",
            "Class w/ Course Room Teach", "Stu(Par) RegVer Paid", "Teach RegVerEnr w/ Course", "Trans (Par) w/ Reg ID",
            "Att Record w/ Class Teach Room", "Stu w/ Sac Course", "Class w/ Room Lang Teach", "Contact Stu (Par) w/ Reg ID",
            "Contact Stu w/ 2 Abs", "Contact Stu by Course", "Contact Teach w/ Class Room Course", "Contact Stu w/ 3 Abs",
            "ESP Class w/ Room Lang Course", "Stu w/ Communion Class Room", "Stu w/ ConfTwo Class Room", "Contact Stu (Par) RegVer Not Paid",
            "Course w/ Class Teach", "Stu w/ Baptism Class Room", "Stu (18) RegVer Paid", "ENG Stu w/ Class", "Stu (Par) Trans w/ Date Amt RegID",
            "Adm User w/ VerStat RegDate", "Stu w/ RICA Class Room", "Class w/ Room Lang Teach", "Stu w/ ESP Class",
            "Teach User w/ VerStat Reg Date", "Teach VerStat w/ Adm", "Stu w/ Conf Class Room", "Stu w/ Sac Not Enroll",
            "Contact Stu (18) RegVer Not Paid", "Stu (18) Trans w/ Date Amt RegID", "Stu w/ VerStat Adm", "ENG Teach w/ Class",
            "Contact Teach w/ Class Course", "Contact Stu w/ Parents Class", "ESP Teach w/ Class", "ENG Class w/ Room Lang Course",
            "Par Not Paid w/ Mar Sep", "Stu w/ LilFriends Class Room", "Par Not Paid w/ Mar Divorced", "Par Not Paid w/ Mar Widow",
            "Par Not Paid w/ Mar Married"};

    private String[] reportPerson = new String[]{"repo_dao_1", "repo_dao_2", "repo_dao_3", "repo_dao_4", "repo_dao_5",
            "repo_haro_1", "repo_haro_2", "repo_haro_3", "repo_haro_4", "repo_haro_5", "repo_garcia_1", "repo_garcia_2",
            "repo_garcia_3", "repo_garcia_4", "repo_garcia_5", "repo_vu_1", "repo_vu_2", "repo_vu_3", "repo_vu_4", "repo_vu_5",
            "repo_deluna_1", "repo_deluna_2", "repo_deluna_3", "repo_deluna_4", "repo_deluna_5", "repo_martin_1", "repo_martin_2",
            "repo_martin_3", "repo_martin_4", "repo_martin_5", "repo_mcallister_1", "repo_mcallister_2", "repo_mcallister_3",
            "repo_mcallister_4", "repo_mcallister_5", "repo_evans_1", "repo_evans_2", "repo_evans_3", "repo_evans_4",
            "repo_evans_5", "repo_mccormick_1", "repo_mccormick_3", "repo_mccormick_3", "repo_mccormick_4", "repo_mccormick_5"};

    //private String[] fxml = new String[]{

    public void initialize(URL url, ResourceBundle rb)
    {
        reportSelectionBox.getItems().addAll(reports);
    }

    public void submitButtonPushed(ActionEvent event) throws IOException
    {
        for (int i = 0; i < reports.length; i++)
        {
            if (reportSelectionBox.getValue().contentEquals(reports[i]))
            {
                System.out.println(reports[i]);
                System.out.println(reportPerson[i]);
                String reportLink = reportPerson[i] + ".fxml";
                System.out.println(reportLink);
                String reportName = reports[i] + " Report";
                System.out.println(reportName);

                Parent root = FXMLLoader.load(getClass().getResource(reportLink));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle(reportName);
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }
    }

    public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Information/tableSelection.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Table Selection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}