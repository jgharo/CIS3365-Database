public class UserManageController {
    ObservableList<AdminInfo> adminInfo = FXCollections.observableArrayList();

try {

        Connection con = DBconnect.getConnection();

        CallableStatement stmtAdmin = con.prepareCall(" ");

        ResultSet rs = stmtAdmin.executeQuery();
        while (rs.next()) {
            adminInfo.add(new display_student_model(rs.getString("MAdm_ID"), rs.getString("Adm_FName"), rs.getString("Adm_LName"), rs.getString("Adm_DOB"),
                    rs.getString("Adm_Phone"), rs.getString("Adm_Address"), rs.getString("Adm_Email")));


        }

    } catch (SQLException ex) {
        Logger.getLogger(display_student_controller.class.getName()).log(Level.SEVERE, null, ex);

    }
