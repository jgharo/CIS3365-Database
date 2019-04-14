package report;


public class repo_evans_2 {

    String Teach_ID, Teach_FName, Teach_LName, Teach_Address, Teach_Email, Class_Name, Class_Room, Course_Name;

    public repo_evans_2(String Teach_ID, String Teach_FName, String Teach_LName, String Teach_Address, String Teach_Email, String Class_Name, String Class_Room, String Course_Name) {
        this.Teach_ID = Teach_ID;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;
        this.Teach_Address= Teach_Address;
        this.Teach_Email = Teach_Email;
        this.Class_Name = Class_Name;
        this.Class_Room= Class_Room;
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

    public String getTeach_Address() {
        return Teach_Address;
    }

    public void setTeach_Address(String teach_Address) {
        Teach_Address = teach_Address;
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

    public String getClass_Room() {
        return Class_Room;
    }

    public void setClass_Room(String class_Room) {
        Class_Room = class_Room;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }
}