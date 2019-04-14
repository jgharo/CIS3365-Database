package report;


public class repo_mcallister_2 {

    String Stu_ID, Class_ID, Course_ID, Stu_FName, Stu_LName, Sac_Type;

    public repo_mcallister_2(String Stu_ID, String Class_ID, String Course_ID, String Stu_FName, String Stu_LName, String Sac_Type) {
        this.Stu_ID = Stu_ID;
        this.Class_ID = Class_ID;
        this.Course_ID = Course_ID;
        this.Stu_FName= Stu_FName;
        this.Stu_LName = Stu_LName;
        this.Sac_Type= Sac_Type;


    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
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

    public String getSac_Type() {
        return Sac_Type;
    }

    public void setSac_Type(String sac_Type) {
        Sac_Type = sac_Type;
    }
}