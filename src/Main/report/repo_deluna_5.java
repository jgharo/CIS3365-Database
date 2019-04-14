package report;


public class repo_deluna_5 {

    String Adm_ID, Adm_User, Adm_FName, Adm_LName, Adm_Verify, AReg_Date;

    public repo_deluna_5(String Adm_ID, String Adm_User, String Adm_FName, String Adm_LName, String Adm_Verify, String AReg_Date) {
        this.Adm_ID = Adm_ID;
        this.Adm_User = Adm_User;
        this.Adm_FName = Adm_FName;
        this.Adm_LName = Adm_LName;
        this.Adm_Verify= Adm_Verify;
        this.AReg_Date = AReg_Date;


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

    public String getAdm_FName() {
        return Adm_FName;
    }

    public void setAdm_FName(String adm_FName) {
        Adm_FName = adm_FName;
    }

    public String getAdm_LName() {
        return Adm_LName;
    }

    public void setAdm_LName(String adm_LName) {
        Adm_LName = adm_LName;
    }

    public String getAdm_Verify() {
        return Adm_Verify;
    }

    public void setAdm_Verify(String adm_Verify) {
        Adm_Verify = adm_Verify;
    }

    public String getAReg_Date() {
        return AReg_Date;
    }

    public void setAReg_Date(String AReg_Date) {
        this.AReg_Date = AReg_Date;
    }
}