package Information;

public class TeacherAccount {

    String Teach_Acc_ID,Teach_ID, Teach_User, Teach_Pass;

    public TeacherAccount(String Teach_Acc_ID, String Teach_ID, String Teach_User, String Teach_Pass){
        this.Teach_Acc_ID=Teach_Acc_ID;
        this.Teach_ID=Teach_ID;
        this.Teach_User=Teach_User;
        this.Teach_Pass=Teach_Pass;

    }

    public String getTeach_Acc_ID() {
        return Teach_Acc_ID;
    }

    public void setTeach_Acc_ID(String teach_Acc_ID) {
        Teach_Acc_ID = teach_Acc_ID;
    }

    public String getTeach_ID() {
        return Teach_ID;
    }

    public void setTeach_ID(String teach_ID) {
        Teach_ID = teach_ID;
    }

    public String getTeach_User() {
        return Teach_User;
    }

    public void setTeach_User(String teach_User) {
        Teach_User = teach_User;
    }

    public String getTeach_Pass() {
        return Teach_Pass;
    }

    public void setTeach_Pass(String teach_Pass) {
        Teach_Pass = teach_Pass;
    }
}
