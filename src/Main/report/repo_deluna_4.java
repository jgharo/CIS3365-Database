package report;


public class repo_deluna_4 {

    String PReg_ID, Trans_Par_ID, Trans_Amt, Trans_Date;

    public repo_deluna_4(String PReg_ID, String Trans_Par_ID, String Trans_Amt, String Trans_Date) {
        this.PReg_ID = PReg_ID;
        this.Trans_Par_ID = Trans_Par_ID;
        this.Trans_Amt = Trans_Amt;
        this.Trans_Date = Trans_Date;


    }

    public String getPReg_ID() {
        return PReg_ID;
    }

    public void setPReg_ID(String PReg_ID) {
        this.PReg_ID = PReg_ID;
    }

    public String getTrans_Par_ID() {
        return Trans_Par_ID;
    }

    public void setTrans_Par_ID(String trans_Par_ID) {
        Trans_Par_ID = trans_Par_ID;
    }

    public String getTrans_Amt() {
        return Trans_Amt;
    }

    public void setTrans_Amt(String trans_Amt) {
        Trans_Amt = trans_Amt;
    }

    public String getTrans_Date() {
        return Trans_Date;
    }

    public void setTrans_Date(String trans_Date) {
        Trans_Date = trans_Date;
    }
}