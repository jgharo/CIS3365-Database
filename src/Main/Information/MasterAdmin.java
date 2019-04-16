package Information;

public class MasterAdmin {
    String MAdm_ID, MAdm_FName, MAdm_LName, MAdm_Email, MAdm_Phone, Date_Mod;

    public MasterAdmin(String madm_ID, String madm_FName, String madm_LName, String madm_Email, String madm_Phone, String date_Mod) {
        MAdm_ID = madm_ID;
        MAdm_FName = madm_FName;
        MAdm_LName = madm_LName;
        MAdm_Email = madm_Email;
        MAdm_Phone = madm_Phone;
        Date_Mod = date_Mod;
    }

    public String getMAdm_ID() {
        return MAdm_ID;
    }

    public void setMAdm_ID(String MAdm_ID) {
        this.MAdm_ID = MAdm_ID;
    }

    public String getMAdm_FName() {
        return MAdm_FName;
    }

    public void setMAdm_FName(String MAdm_FName) {
        this.MAdm_FName = MAdm_FName;
    }

    public String getMAdm_LName() {
        return MAdm_LName;
    }

    public void setMAdm_LName(String MAdm_LName) {
        this.MAdm_LName = MAdm_LName;
    }

    public String getMAdm_Email() {
        return MAdm_Email;
    }

    public void setMAdm_Email(String MAdm_Email) {
        this.MAdm_Email = MAdm_Email;
    }

    public String getMAdm_Phone() {
        return MAdm_Phone;
    }

    public void setMAdm_Phone(String MAdm_Phone) {
        this.MAdm_Phone = MAdm_Phone;
    }

    public String getDate_Mod() {
        return Date_Mod;
    }

    public void setDate_Mod(String date_Mod) {
        Date_Mod = date_Mod;
    }
}
