package report;


public class repo_haro_3 {

    String Att_ID, Att_Date, Teach_ID, Teach_FName, Teach_LName,  Class_Room;

    public repo_haro_3(String Att_ID, String Att_Date, String Teach_ID, String Teach_FName, String Teach_LName,String Class_Room) {

        this.Att_ID = Att_ID;
        this.Att_Date = Att_Date;
        this.Teach_ID = Teach_ID;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;
        this.Class_Room=Class_Room;


    }

    public String getAtt_ID() {
        return Att_ID;
    }

    public void setAtt_ID(String att_ID) {
        Att_ID = att_ID;
    }

    public String getAtt_Date() {
        return Att_Date;
    }

    public void setAtt_Date(String att_Date) {
        Att_Date = att_Date;
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

    public String getClass_Room() {
        return Class_Room;
    }

    public void setClass_Room(String class_Room) {
        Class_Room = class_Room;
    }
}