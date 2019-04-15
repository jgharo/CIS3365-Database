package Information;

public class TransactionStuHistory {

    String Trans_Stu_Hist_ID,Trans_Stu_ID, Trans_Date;

    public TransactionStuHistory(String Trans_Stu_Hist_ID, String Trans_Stu_ID, String Trans_Date){
        this.Trans_Stu_Hist_ID=Trans_Stu_ID;
        this.Trans_Stu_ID=Trans_Stu_ID;
        this.Trans_Date=Trans_Date;



    }

    public String getTrans_Stu_Hist_ID() {
        return Trans_Stu_Hist_ID;
    }

    public void setTrans_Stu_Hist_ID(String trans_Stu_Hist_ID) {
        Trans_Stu_Hist_ID = trans_Stu_Hist_ID;
    }

    public String getTrans_Stu_ID() {
        return Trans_Stu_ID;
    }

    public void setTrans_Stu_ID(String trans_Stu_ID) {
        Trans_Stu_ID = trans_Stu_ID;
    }

    public String getTrans_Date() {
        return Trans_Date;
    }

    public void setTrans_Date(String trans_Date) {
        Trans_Date = trans_Date;
    }
}
