package Information;

public class AdminVerification {

    String MAdm_ID, Adm_ID, Adm_Verify;

    public AdminVerification(String MAdm_ID, String Adm_ID, String Adm_Verify){
        this.MAdm_ID=MAdm_ID;
        this.Adm_ID=Adm_ID;
        this.Adm_Verify=Adm_Verify;

    }

    public String getMAdm_ID() {
        return MAdm_ID;
    }

    public void setMAdm_ID(String MAdm_ID) {
        this.MAdm_ID = MAdm_ID;
    }

    public String getAdm_ID() {
        return Adm_ID;
    }

    public void setAdm_ID(String adm_ID) {
        Adm_ID = adm_ID;
    }

    public String getAdm_Verify() {
        return Adm_Verify;
    }

    public void setAdm_Verify(String adm_Verify) {
        Adm_Verify = adm_Verify;
    }
}
