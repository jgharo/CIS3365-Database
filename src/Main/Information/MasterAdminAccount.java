package Information;

public class MasterAdminAccount {

    String MAdm_Acc_ID,MAdm_ID, MAdm_User, MAdm_Pass;

    public MasterAdminAccount(String MAdm_Acc_ID, String MAdm_ID, String MAdm_User, String MAdm_Pass){
        this.MAdm_Acc_ID=MAdm_Acc_ID;
        this.MAdm_ID=MAdm_ID;
        this.MAdm_User=MAdm_User;
        this.MAdm_Pass=MAdm_Pass;

    }

    public String getMAdm_Acc_ID() {
        return MAdm_Acc_ID;
    }

    public void setMAdm_Acc_ID(String MAdm_Acc_ID) {
        this.MAdm_Acc_ID = MAdm_Acc_ID;
    }

    public String getMAdm_ID() {
        return MAdm_ID;
    }

    public void setMAdm_ID(String MAdm_ID) {
        this.MAdm_ID = MAdm_ID;
    }

    public String getMAdm_User() {
        return MAdm_User;
    }

    public void setMAdm_User(String MAdm_User) {
        this.MAdm_User = MAdm_User;
    }

    public String getMAdm_Pass() {
        return MAdm_Pass;
    }

    public void setMAdm_Pass(String MAdm_Pass) {
        this.MAdm_Pass = MAdm_Pass;
    }
}
