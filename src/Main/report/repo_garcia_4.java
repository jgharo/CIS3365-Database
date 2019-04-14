package report;


public class repo_garcia_4 {

    String Teach_ID, Teach_FName, Teach_LName, Teach_Phone, Teach_Email, Class_Name, Course_Name;

    public repo_garcia_4 (String Teach_ID, String Teach_FName, String Teach_LName, String Teach_Phone, String Teach_Email, String Class_Name, String Course_Name) {
        this.Teach_ID = Teach_ID;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;
        this.Teach_Phone= Teach_Phone;
        this.Teach_Email= Teach_Email;
        this.Course_Name=Course_Name;


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

    public String getTeach_Phone() {
        return Teach_Phone;
    }

    public void setTeach_Phone(String teach_Phone) {
        Teach_Phone = teach_Phone;
    }

    public String getTeach_Email() {
        return Teach_Email;
    }

    public void setTeach_Email(String teach_Email) {
        Teach_Email = teach_Email;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public void setClass_Name(String class_Name) {
        Class_Name = class_Name;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }
}