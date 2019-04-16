package Information;

public class Teacher {
    String Teach_ID, Teach_FName, Teach_LName, Teach_DOB,  Teach_Email, Teach_Phone, Teach_Address, Teach_Lang, Teach_Virtus, Date_Mod;

    public Teacher(String teach_ID, String teach_FName, String teach_LName, String teach_DOB, String teach_Email, String teach_Phone, String teach_Address, String teach_Lang, String teach_Virtus, String date_Mod) {
        Teach_ID = teach_ID;
        Teach_FName = teach_FName;
        Teach_LName = teach_LName;
        Teach_DOB = teach_DOB;
        Teach_Email = teach_Email;
        Teach_Phone = teach_Phone;
        Teach_Address = teach_Address;
        Teach_Lang = teach_Lang;
        Teach_Virtus = teach_Virtus;
        Date_Mod = date_Mod;
    }

    public String getTeach_ID() {
        return Teach_ID;
    }

    public void setTeach_ID(String teach_ID) {
        Teach_ID = teach_ID;
    }

    public String getTeach_FName() {
        return Teach_FName;
    }

    public void setTeach_FName(String teach_FName) {
        Teach_FName = teach_FName;
    }

    public String getTeach_LName() {
        return Teach_LName;
    }

    public void setTeach_LName(String teach_LName) {
        Teach_LName = teach_LName;
    }

    public String getTeach_DOB() {
        return Teach_DOB;
    }

    public void setTeach_DOB(String teach_DOB) {
        Teach_DOB = teach_DOB;
    }

    public String getTeach_Email() {
        return Teach_Email;
    }

    public void setTeach_Email(String teach_Email) {
        Teach_Email = teach_Email;
    }

    public String getTeach_Phone() {
        return Teach_Phone;
    }

    public void setTeach_Phone(String teach_Phone) {
        Teach_Phone = teach_Phone;
    }

    public String getTeach_Address() {
        return Teach_Address;
    }

    public void setTeach_Address(String teach_Address) {
        Teach_Address = teach_Address;
    }

    public String getTeach_Lang() {
        return Teach_Lang;
    }

    public void setTeach_Lang(String teach_Lang) {
        Teach_Lang = teach_Lang;
    }

    public String getTeach_Virtus() {
        return Teach_Virtus;
    }

    public void setTeach_Virtus(String teach_Virtus) {
        Teach_Virtus = teach_Virtus;
    }

    public String getDate_Mod() {
        return Date_Mod;
    }

    public void setDate_Mod(String date_Mod) {
        Date_Mod = date_Mod;
    }
}
