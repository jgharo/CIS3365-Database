package Information;

public class StudentVerification {

    String Adm_ID,Stu_ID, Stu_Verify;

    public StudentVerification(String Adm_ID, String Stu_ID, String Stu_Verify){
        this.Adm_ID=Adm_ID;
        this.Stu_ID=Stu_ID;
        this.Stu_Verify=Stu_Verify;

    }

    public String getAdm_ID() {
        return Adm_ID;
    }

    public void setAdm_ID(String adm_ID) {
        Adm_ID = adm_ID;
    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getStu_Verify() {
        return Stu_Verify;
    }

    public void setStu_Verify(String stu_Verify) {
        Stu_Verify = stu_Verify;
    }
}
