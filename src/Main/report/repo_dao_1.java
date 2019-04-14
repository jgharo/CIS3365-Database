package report;


public class repo_dao_1 {

    String Stu_ID, Stu_FName, Stu_LName, Stu_Verify, Class_Name, Class_Room;

    public repo_dao_1(String Stu_ID, String Stu_FName, String Stu_LName, String Stu_Verify, String Class_Name, String Class_Room) {
        this.Stu_ID = Stu_ID;
        this.Stu_FName = Stu_FName;
        this.Stu_LName = Stu_LName;
        this.Stu_Verify = Stu_Verify;
        this.Class_Name= Class_Name;
        this.Class_Room= Class_Room;


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

    public String getStu_Verify() {
        return Stu_Verify;
    }

    public void setStu_Verify(String stu_Verify) {
        Stu_Verify = stu_Verify;
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
}