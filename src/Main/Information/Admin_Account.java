package Information;

public class Admin_Account {
    String Adm_Acc_ID, Adm_ID, Adm_User, Adm_Pass;

    public Admin_Account(String adm_Acc_ID, String adm_ID, String adm_User, String adm_Pass) {
        Adm_Acc_ID = adm_Acc_ID;
        Adm_ID = adm_ID;
        Adm_User = adm_User;
        Adm_Pass = adm_Pass;
    }

    public String getAdm_Acc_ID() {
        return Adm_Acc_ID;
    }

    public void setAdm_Acc_ID(String adm_Acc_ID) {
        Adm_Acc_ID = adm_Acc_ID;
    }

    public String getAdm_ID() {
        return Adm_ID;
    }

    public void setAdm_ID(String adm_ID) {
        Adm_ID = adm_ID;
    }

    public String getAdm_User() {
        return Adm_User;
    }

    public void setAdm_User(String adm_User) {
        Adm_User = adm_User;
    }

    public String getAdm_Pass() {
        return Adm_Pass;
    }

    public void setAdm_Pass(String adm_Pass) {
        Adm_Pass = adm_Pass;
    }
}
