package Information;

public class InformationAdmin {
    String Adm_ID;
    String Adm_FName;
    String Adm_LName;
    String Adm_Phone;
    String Adm_Address;
    String Adm_Email;
    String Date_Mod;

    public InformationAdmin(String adm_ID, String adm_FName, String adm_LName, String adm_Phone, String adm_Address, String adm_Email, String date_Mod) {
        this.Adm_ID = adm_ID;
        this.Adm_FName = adm_FName;
        this.Adm_LName = adm_LName;
        this.Adm_Phone = adm_Phone;
        this.Adm_Address = adm_Address;
        this.Adm_Email = adm_Email;
        this.Date_Mod = date_Mod;
    }
    public String getAdm_ID() {
        return Adm_ID;
    }

    public void setAdm_ID(String adm_ID) {
        Adm_ID = adm_ID;
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

    public String getAdm_Phone() {
        return Adm_Phone;
    }

    public void setAdm_Phone(String adm_Phone) {
        Adm_Phone = adm_Phone;
    }

    public String getAdm_Address() {
        return Adm_Address;
    }

    public void setAdm_Address(String adm_Address) {
        Adm_Address = adm_Address;
    }

    public String getAdm_Email() {
        return Adm_Email;
    }

    public void setAdm_Email(String adm_Email) {
        Adm_Email = adm_Email;
    }

    public String getDate_Mod() {
        return Date_Mod;
    }

    public void setDate_Mod(String date_Mod) {
        Date_Mod = date_Mod;
    }



}
