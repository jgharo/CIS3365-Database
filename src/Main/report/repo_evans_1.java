package report;


public class repo_evans_1 {

    String Teach_ID, Teach_FName, Teach_LName, Teach_Lang, Class_Name, Class_Lang, Class_Room;

    public repo_evans_1(String Teach_ID, String Teach_FName, String Teach_LName, String Teach_Lang, String Class_Name, String Class_Lang, String Class_Room) {
        this.Teach_ID = Teach_ID;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;
        this.Teach_Lang= Teach_Lang;
        this.Class_Name = Class_Name;
        this.Class_Lang = Class_Lang;
        this.Class_Room = Class_Room;

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

    public String getTeach_Lang() {
        return Teach_Lang;
    }

    public void setTeach_Lang(String teach_Lang) {
        Teach_Lang = teach_Lang;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public void setClass_Name(String class_Name) {
        Class_Name = class_Name;
    }

    public String getClass_Lang() {
        return Class_Lang;
    }

    public void setClass_Lang(String class_Lang) {
        Class_Lang = class_Lang;
    }

    public String getClass_Room() {
        return Class_Room;
    }

    public void setClass_Room(String class_Room) {
        Class_Room = class_Room;
    }
}