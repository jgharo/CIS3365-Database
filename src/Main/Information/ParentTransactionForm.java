package Information;

public class ParentTransactionForm {

    String Trans_Par_ID,PReg_ID, Trans_Amt, Trans_Remain, Due_Date;

    public ParentTransactionForm(String Trans_Par_ID, String PReg_ID, String Trans_Amt, String Trans_Remain, String Due_Date){
        this.Trans_Par_ID=Trans_Par_ID;
        this.PReg_ID=PReg_ID;
        this.Trans_Amt=Trans_Amt;
        this.Trans_Remain=Trans_Remain;

    }

    public String getTrans_Par_ID() {
        return Trans_Par_ID;
    }

    public void setTrans_Par_ID(String trans_Par_ID) {
        Trans_Par_ID = trans_Par_ID;
    }

    public String getPReg_ID() {
        return PReg_ID;
    }

    public void setPReg_ID(String preg_ID) {
        PReg_ID = preg_ID;
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
