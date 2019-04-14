package Information;

public class Attendance_Record {
    String Att_ID, Class_ID, Att_Date;

    public Attendance_Record(String att_ID, String class_ID, String att_Date) {
        Att_ID = att_ID;
        Class_ID = class_ID;
        Att_Date = att_Date;
    }

    public String getAtt_ID() {
        return Att_ID;
    }

    public void setAtt_ID(String att_ID) {
        Att_ID = att_ID;
    }

    public String getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(String class_ID) {
        Class_ID = class_ID;
    }

    public String getAtt_Date() {
        return Att_Date;
    }

    public void setAtt_Date(String att_Date) {
        Att_Date = att_Date;
    }
}
