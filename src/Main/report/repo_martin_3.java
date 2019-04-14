package report;


public class repo_martin_3 {

    String Stu_ID, Stu_FName, Stu_LName, Class_Name, Class_Room, Class_Lang;

    public repo_martin_3(String Stu_ID, String Stu_FName, String Stu_LName, String Class_Name, String Class_Room, String Class_Lang) {
        this.Stu_ID = Stu_ID;
        this.Stu_FName = Stu_FName;
        this.Stu_LName = Stu_LName;
        this.Class_Name= Class_Name;
        this.Class_Room = Class_Room;
        this.Class_Lang = Class_Lang;


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

    public String getClass_Lang() {
        return Class_Lang;
    }

    public void setClass_Lang(String class_Lang) {
        Class_Lang = class_Lang;
    }
}