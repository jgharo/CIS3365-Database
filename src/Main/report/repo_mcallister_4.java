package report;


public class repo_mcallister_4 {

    String Stu_ID, SReg_ID, Trans_Stu_ID, Trans_Amt, Trans_Date;

    public repo_mcallister_4(String Stu_ID, String SReg_ID, String Trans_Stu_ID, String Trans_Amt, String Trans_Date) {
        this.Stu_ID = Stu_ID;
        this.SReg_ID = SReg_ID;
        this.Trans_Stu_ID = Trans_Stu_ID;
        this.Trans_Amt= Trans_Amt;
        this.Trans_Date = Trans_Date;

    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getSReg_ID() {
        return SReg_ID;
    }

    public void setSReg_ID(String SReg_ID) {
        this.SReg_ID = SReg_ID;
    }

    public String getTrans_Stu_ID() {
        return Trans_Stu_ID;
    }

    public void setTrans_Stu_ID(String trans_Stu_ID) {
        Trans_Stu_ID = trans_Stu_ID;
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