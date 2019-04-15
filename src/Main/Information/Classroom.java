package Information;

public class Classroom {

    String ClassRoom_ID, Class_ID, Class_Room;

    public Classroom(String ClassRoom_ID, String Class_ID, String Class_Room) {
        this.ClassRoom_ID = ClassRoom_ID;
        this.Class_ID = Class_ID;
        this.Class_Room = Class_Room;
    }

    public String getClassRoom_ID() {
        return ClassRoom_ID;
    }

    public void setClassRoom_ID(String classRoom_ID) {
        ClassRoom_ID = classRoom_ID;
    }

    public String getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(String class_ID) {
        Class_ID = class_ID;
    }

    public String getClass_Room() {
        return Class_Room;
    }

    public void setClass_Room(String class_Room) {
        Class_Room = class_Room;
    }
}
