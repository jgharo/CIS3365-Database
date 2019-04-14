package Information;

public class Class {
    String Class_ID, Teach_ID, Course_ID, Class_Name;

    public Class(String class_ID, String teach_ID, String course_ID, String class_Name) {
        Class_ID = class_ID;
        Teach_ID = teach_ID;
        Course_ID = course_ID;
        Class_Name = class_Name;
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

    public String getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(String course_ID) {
        Course_ID = course_ID;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public void setClass_Name(String class_Name) {
        Class_Name = class_Name;
    }
}
