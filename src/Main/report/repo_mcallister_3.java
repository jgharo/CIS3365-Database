package report;


public class repo_mcallister_3 {

    String Stu_ID, Stu_FName, Stu_LName, Stu_Phone, Stu_Email, Stu_Verify, Trans_Remain;

    public repo_mcallister_3(String Stu_ID, String Stu_FName, String Stu_LName, String Stu_Phone, String Stu_Email, String Stu_Verify, String Trans_Remain) {
        this.Stu_ID = Stu_ID;
        this.Stu_FName = Stu_FName;
        this.Stu_LName = Stu_LName;
        this.Stu_Phone= Stu_Phone;
        this.Stu_Email = Stu_Email;
        this.Stu_Verify= Stu_Verify;
        this.Trans_Remain = Trans_Remain;

    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getStu_FName() {
        return Stu_FName;
    }

    public void setStu_FName(String stu_FName) {
        Stu_FName = stu_FName;
    }

    public String getStu_LName() {
        return Stu_LName;
    }

    public void setStu_LName(String stu_LName) {
        Stu_LName = stu_LName;
    }

    public String getStu_Phone() {
        return Stu_Phone;
    }

    public void setStu_Phone(String stu_Phone) {
        Stu_Phone = stu_Phone;
    }

    public String getStu_Email() {
        return Stu_Email;
    }

    public void setStu_Email(String stu_Email) {
        Stu_Email = stu_Email;
    }

    public String getStu_Verify() {
        return Stu_Verify;
    }

    public void setStu_Verify(String stu_Verify) {
        Stu_Verify = stu_Verify;
    }

    public String getTrans_Remain() {
        return Trans_Remain;
    }

    public void setTrans_Remain(String trans_Remain) {
        Trans_Remain = trans_Remain;
    }
}