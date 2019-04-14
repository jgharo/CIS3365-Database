package report;


public class repo_haro_2 {

    String Trans_Par_ID, PReg_ID, Trans_Remain, Trans_Date;

    public repo_haro_2(String Trans_Par_ID, String PReg_ID, String Trans_Remain, String Trans_Date) {

        this.Trans_Par_ID = Trans_Par_ID;
        this.PReg_ID = PReg_ID;
        this.Trans_Remain = Trans_Remain;
        this.Trans_Date = Trans_Date;


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

    public void setPReg_ID(String PReg_ID) {
        this.PReg_ID = PReg_ID;
    }

    public String getTrans_Remain() {
        return Trans_Remain;
    }

    public void setTrans_Remain(String trans_Remain) {
        Trans_Remain = trans_Remain;
    }

    public String getTrans_Date() {
        return Trans_Date;
    }

    public void setTrans_Date(String trans_Date) {
        Trans_Date = trans_Date;
    }
}