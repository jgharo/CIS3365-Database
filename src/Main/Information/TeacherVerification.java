package Information;

public class TeacherVerification {

    String Adm_ID,Teach_ID, Teach_Verify;

    public TeacherVerification(String Adm_ID, String Teach_ID, String Teach_Verify){
        this.Adm_ID=Adm_ID;
        this.Teach_ID=Teach_ID;
        this.Teach_Verify=Teach_Verify;

    }

    public String getAdm_ID() {
        return Adm_ID;
    }

    public void setAdm_ID(String adm_ID) {
        Adm_ID = adm_ID;
    }

    public String getTeach_ID() {
        return Teach_ID;
    }

    public void setTeach_ID(String teach_ID) {
        Teach_ID = teach_ID;
    }

    public String getTeach_Verify() {
        return Teach_Verify;
    }

    public void setTeach_Verify(String teach_Verify) {
        Teach_Verify = teach_Verify;
    }
}
