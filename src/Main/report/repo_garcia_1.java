package report;


public class repo_garcia_1 {

    String Stu_ID, Stu_FName, Stu_LName, Fath_FName, Fath_LName, Moth_FName, Moth_LName, Fath_Phone, Moth_Phone, Trans_Remain;

    public repo_garcia_1(String Stu_ID, String Stu_FName, String Stu_LName, String Fath_FName, String Fath_LName,
                         String Moth_FName, String Moth_LName, String Fath_Phone,String Moth_Phone, String Trans_Remain) {
        this.Stu_ID = Stu_ID;
        this.Stu_FName = Stu_FName;
        this.Stu_LName = Stu_LName;
        this.Fath_FName = Fath_FName;
        this.Fath_LName = Fath_LName;
        this.Moth_FName = Moth_FName;
        this.Moth_LName = Moth_LName;
        this.Fath_Phone= Fath_Phone;
        this.Moth_Phone= Moth_Phone;
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

    public String getFath_FName() {
        return Fath_FName;
    }

    public void setFath_FName(String fath_FName) {
        Fath_FName = fath_FName;
    }

    public String getFath_LName() {
        return Fath_LName;
    }

    public void setFath_LName(String fath_LName) {
        Fath_LName = fath_LName;
    }

    public String getMoth_FName() {
        return Moth_FName;
    }

    public void setMoth_FName(String moth_FName) {
        Moth_FName = moth_FName;
    }

    public String getMoth_LName() {
        return Moth_LName;
    }

    public void setMoth_LName(String moth_LName) {
        Moth_LName = moth_LName;
    }

    public String getFath_Phone() {
        return Fath_Phone;
    }

    public void setFath_Phone(String fath_Phone) {
        Fath_Phone = fath_Phone;
    }

    public String getMoth_Phone() {
        return Moth_Phone;
    }

    public void setMoth_Phone(String moth_Phone) {
        Moth_Phone = moth_Phone;
    }

    public String getTrans_Remain() {
        return Trans_Remain;
    }

    public void setTrans_Remain(String trans_Remain) {
        Trans_Remain = trans_Remain;
    }
}