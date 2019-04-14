package Information;

public class informationStudent {
    String Stu_ID, Par_ID, Class_ID, Course_ID,Stu_FName, Stu_LName, Stu_DOB, Stu_Email, Stu_Phone, Stu_Address, Stu_Lang, Stu_Abs,Date_Mod;

    public informationStudent(String Stu_ID, String Par_ID, String Class_ID, String Course_ID, String Stu_FName, String Stu_LName, String Stu_DOB, String Stu_Email, String Stu_Phone, String Stu_Address, String Stu_Lang, String Stu_Abs, String Date_Mod ){
        this.Stu_ID= Stu_ID;
        this.Par_ID= Par_ID;
        this.Class_ID=Class_ID;
        this.Course_ID=Course_ID;
        this.Stu_FName=Stu_FName;
        this.Stu_LName=Stu_LName;
        this.Stu_DOB=Stu_DOB;
        this.Stu_Email=Stu_Email;
        this.Stu_Phone=Stu_Phone;
        this.Stu_Address=Stu_Address;
        this.Stu_Lang=Stu_Lang;
        this.Stu_Abs=Stu_Abs;
        this.Date_Mod=Date_Mod;

    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getPar_ID() {
        return Par_ID;
    }

    public void setPar_ID(String par_ID) {
        Par_ID = par_ID;
    }

    public String getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(String class_ID) {
        Class_ID = class_ID;
    }

    public String getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(String course_ID) {
        Course_ID = course_ID;
    }

    public String getStu_FName() {
        return Stu_FName;
    }

    public void setStu_FName(String stu_FName) {
        Stu_FName = stu_FName;
    }

    public String getStu_LName() {
        return Stu_LName;
    }

    public void setStu_LName(String stu_LName) {
        Stu_LName = stu_LName;
    }

    public String getStu_DOB() {
        return Stu_DOB;
    }

    public void setStu_DOB(String stu_DOB) {
        Stu_DOB = stu_DOB;
    }

    public String getStu_Email() {
        return Stu_Email;
    }

    public void setStu_Email(String stu_Email) {
        Stu_Email = stu_Email;
    }

    public String getStu_Phone() {
        return Stu_Phone;
    }

    public void setStu_Phone(String stu_Phone) {
        Stu_Phone = stu_Phone;
    }

    public String getStu_Address() {
        return Stu_Address;
    }

    public void setStu_Address(String stu_Address) {
        Stu_Address = stu_Address;
    }

    public String getStu_Lang() {
        return Stu_Lang;
    }

    public void setStu_Lang(String stu_Lang) {
        Stu_Lang = stu_Lang;
    }

    public String getStu_Abs() {
        return Stu_Abs;
    }

    public void setStu_Abs(String stu_Abs) {
        Stu_Abs = stu_Abs;
    }

    public String getDate_Mod() {
        return Date_Mod;
    }

    public void setDate_Mod(String date_Mod) {
        Date_Mod = date_Mod;
    }
}
