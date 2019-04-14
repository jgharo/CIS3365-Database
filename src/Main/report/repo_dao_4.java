package report;


public class repo_dao_4 {

    String Class_ID, Class_Name, Course_Name, Teach_FName, Teach_LName, Class_Room;

    public repo_dao_4(String Class_ID, String Class_Name, String Course_Name, String Teach_FName, String Teach_LName, String Class_Room) {
        this.Class_ID = Class_ID;
        this.Class_Name = Class_Name;
        this.Course_Name = Course_Name;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;
        this.Class_Room = Class_Room;


    }

    public String getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(String class_ID) {
        Class_ID = class_ID;
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

    public String getClass_Room() {
        return Class_Room;
    }

    public void setClass_Room(String class_Room) {
        Class_Room = class_Room;
    }
}