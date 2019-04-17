package report;


public class repo_garcia_3 {

    String Course_Name, Stu_ID, Stu_FName, Stu_LName, Stu_Phone, Fath_FName, Fath_LName, Moth_FName, Moth_LName, Class_Name;

    public repo_garcia_3(String Course_Name, String Stu_ID, String Stu_FName, String Stu_LName, String Stu_Phone, String Fath_FName, String Fath_LName,
                     String Moth_FName, String Moth_LName, String Class_Name) {
        this.Course_Name=Course_Name;
        this.Stu_ID = Stu_ID;
        this.Stu_FName = Stu_FName;
        this.Stu_LName = Stu_LName;
        this.Stu_Phone = Stu_Phone;
        this.Fath_FName = Fath_FName;
        this.Fath_LName = Fath_LName;
        this.Moth_FName = Moth_FName;
        this.Moth_LName = Moth_LName;
        this.Class_Name = Class_Name;

    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
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

    public String getStu_Phone() {
        return Stu_Phone;
    }

    public void setStu_Phone(String stu_Phone) {
        Stu_Phone = stu_Phone;
    }

    public String getFath_FName() {
        return Fath_FName;
    }

    public void setFath_FName(String fath_FName) {
        Fath_FName = fath_FName;
    }

    public String getFath_LName() {
        return Fath_LName;
    }

    public void setFath_LName(String fath_LName) {
        Fath_LName = fath_LName;
    }

    public String getMoth_FName() {
        return Moth_FName;
    }

    public void setMoth_FName(String moth_FName) {
        Moth_FName = moth_FName;
    }

    public String getMoth_LName() {
        return Moth_LName;
    }

    public void setMoth_LName(String moth_LName) {
        Moth_LName = moth_LName;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public void setClass_Name(String class_Name) {
        Class_Name = class_Name;
    }
}