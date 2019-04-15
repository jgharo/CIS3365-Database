package Information;

public class AdminAccount {

    String Adm_Acc_ID,Adm_ID, Adm_User, Adm_Pass;

    public AdminAccount(String Adm_Acc_ID, String Adm_ID, String Adm_User, String Adm_Pass){
        this.Adm_Acc_ID=Adm_Acc_ID;
        this.Adm_ID=Adm_ID;
        this.Adm_User=Adm_User;
        this.Adm_Pass=Adm_Pass;

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
