package report;


public class repo_dao_3 {

    String Teach_ID, Teach_FName, Teach_LName, Teach_Verify, TReg_Date;

    public repo_dao_3 (String Teach_ID, String Teach_FName, String Teach_LName, String Teach_Verify, String TReg_Date) {
        this.Teach_ID = Teach_ID;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;
        this.Teach_Verify= Teach_Verify;
        this.TReg_Date= TReg_Date;



    }

    public String getTeach_ID() {
        return Teach_ID;
    }

    public void setTeach_ID(String teach_ID) {
        Teach_ID = teach_ID;
    }

    public String getTeach_FName() {
        return Teach_FName;
    }

    public void setTeach_FName(String teach_FName) {
        Teach_FName = teach_FName;
    }

    public String getTeach_LName() {
        return Teach_LName;
    }

    public void setTeach_LName(String teach_LName) {
        Teach_LName = teach_LName;
    }

    public String getTeach_Verify() {
        return Teach_Verify;
    }

    public void setTeach_Verify(String teach_Verify) {
        Teach_Verify = teach_Verify;
    }

    public String getTReg_Date() {
        return TReg_Date;
    }

    public void setTReg_Date(String TReg_Date) {
        this.TReg_Date = TReg_Date;
    }
}