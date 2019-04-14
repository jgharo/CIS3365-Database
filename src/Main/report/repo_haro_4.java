package report;


public class repo_haro_4 {

    String Stu_ID, Stu_FName, Stu_LName, Course_Name, Sac_Type;

    public repo_haro_4(String Stu_ID, String Stu_FName, String Stu_LName, String Course_Name, String Sac_Type) {
        this.Stu_ID = Stu_ID;
        this.Stu_FName = Stu_FName;
        this.Stu_LName = Stu_LName;
        this.Course_Name= Course_Name;
        this.Sac_Type = Sac_Type;



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

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    public String getSac_Type() {
        return Sac_Type;
    }

    public void setSac_Type(String sac_Type) {
        Sac_Type = sac_Type;
    }
}