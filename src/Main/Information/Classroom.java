package Information;

public class Classroom {

    String ClassRoom_ID, Class_ID;

    public Classroom(String classRoom_ID, String class_ID) {
        ClassRoom_ID = classRoom_ID;
        Class_ID = class_ID;
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
}
