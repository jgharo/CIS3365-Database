package Information;

public class StudentTransactionForm {

    String Trans_Stu_ID,Stu_ID, Trans_Amt, Trans_Remain, Due_Date;

    public StudentTransactionForm(String Trans_Stu_ID, String Stu_ID, String Trans_Amt, String Trans_Remain, String Due_Date){
        this.Trans_Stu_ID=Trans_Stu_ID;
        this.Stu_ID=Stu_ID;
        this.Trans_Amt=Trans_Amt;
        this.Trans_Remain=Trans_Remain;

    }

    public String getTrans_Stu_ID() {
        return Trans_Stu_ID;
    }

    public void setTrans_Stu_ID(String trans_Stu_ID) {
        Trans_Stu_ID = trans_Stu_ID;
    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getTrans_Amt() {
        return Trans_Amt;
    }

    public void setTrans_Amt(String trans_Amt) {
        Trans_Amt = trans_Amt;
    }

    public String getTrans_Remain() {
        return Trans_Remain;
    }

    public void setTrans_Remain(String trans_Remain) {
        Trans_Remain = trans_Remain;
    }

    public String getDue_Date() {
        return Due_Date;
    }

    public void setDue_Date(String due_Date) {
        Due_Date = due_Date;
    }
}
