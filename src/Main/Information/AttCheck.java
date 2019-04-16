package Information;

public class AttCheck{
    String Att_ID, Stu_ID, Att_Stat;

    public AttCheck(String Att_ID, String Stu_ID ,String Att_Stat) {
        this.Att_ID = Att_ID;
        this.Stu_ID = Stu_ID;
        this.Att_Stat = Att_Stat;
    }

    public String getAtt_ID() {
        return Att_ID;
    }

    public void setAtt_ID(String att_ID) {
        Att_ID = att_ID;
    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getAtt_Stat() {
        return Att_Stat;
    }

    public void setAtt_Stat(String att_Stat) {
        Att_Stat = att_Stat;
    }
}
