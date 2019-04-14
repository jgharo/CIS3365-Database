package report;


public class repo_martin_2 {

    String Class_ID, Teach_ID, Class_Name, Class_Lang, Class_Room;

    public repo_martin_2(String Class_ID, String Teach_ID, String Class_Name, String Class_Lang, String Class_Room) {
        this.Class_ID = Class_ID;
        this.Teach_ID = Teach_ID;
        this.Class_Name = Class_Name;
        this.Class_Lang= Class_Lang;
        this.Class_Room = Class_Room;


    }

    public String getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(String class_ID) {
        Class_ID = class_ID;
    }

    public String getTeach_ID() {
        return Teach_ID;
    }

    public void setTeach_ID(String teach_ID) {
        Teach_ID = teach_ID;
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