package Information;

public class TransactionParHistory {

    String Trans_Par_Hist_ID,Trans_Par_ID, Trans_Date;

    public TransactionParHistory(String Trans_Par_Hist_ID, String Trans_Par_ID, String Trans_Date){
        this.Trans_Par_Hist_ID=Trans_Par_ID;
        this.Trans_Par_ID=Trans_Par_ID;
        this.Trans_Date=Trans_Date;



    }

    public String getTrans_Par_Hist_ID() {
        return Trans_Par_Hist_ID;
    }

    public void setTrans_Par_Hist_ID(String trans_Par_Hist_ID) {
        Trans_Par_Hist_ID = trans_Par_Hist_ID;
    }

    public String getTrans_Par_ID() {
        return Trans_Par_ID;
    }

    public void setTrans_Par_ID(String trans_Par_ID) {
        Trans_Par_ID = trans_Par_ID;
    }

    public String getTrans_Date() {
        return Trans_Date;
    }

    public void setTrans_Date(String trans_Date) {
        Trans_Date = trans_Date;
    }
}
