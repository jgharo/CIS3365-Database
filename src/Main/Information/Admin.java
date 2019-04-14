package Information;

public class Admin {
    String Adm_ID, Adm_FName, Adm_LName, Adm_Email, Adm_Phone, Adm_Address, Date_Mod;

    public Admin(String adm_ID, String adm_FName, String adm_LName, String adm_Email, String adm_Phone, String adm_Address, String date_Mod) {
        Adm_ID = adm_ID;
        Adm_FName = adm_FName;
        Adm_LName = adm_LName;
        Adm_Email = adm_Email;
        Adm_Phone = adm_Phone;
        Adm_Address = adm_Address;
        Date_Mod = date_Mod;
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

    public String getAdm_Email() {
        return Adm_Email;
    }

    public void setAdm_Email(String adm_Email) {
        Adm_Email = adm_Email;
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

    public String getDate_Mod() {
        return Date_Mod;
    }

    public void setDate_Mod(String date_Mod) {
        Date_Mod = date_Mod;
    }
}
