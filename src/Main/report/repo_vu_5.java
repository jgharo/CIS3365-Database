package report;


public class repo_vu_5 {

    String Course_ID, Course_Name, Class_Name, Teach_FName, Teach_LName;

    public repo_vu_5(String Course_ID, String Course_Name, String Class_Name, String Teach_FName, String Teach_LName) {
        this.Course_ID = Course_ID;
        this.Course_Name= Course_Name;
        this.Class_Name = Class_Name;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;


    }

    public String getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(String course_ID) {
        Course_ID = course_ID;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public void setClass_Name(String class_Name) {
        Class_Name = class_Name;
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
}