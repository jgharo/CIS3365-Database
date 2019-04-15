package Information;

public class StudentRegistrationForm {

    String SReg_ID,Stu_ID, SReg_Date;

    public StudentRegistrationForm(String SReg_ID, String Stu_ID, String SReg_Date){
        this.SReg_ID=SReg_ID;
        this.Stu_ID=Stu_ID;
        this.SReg_Date=SReg_Date;

    }

    public String getSReg_ID() {
        return SReg_ID;
    }

    public void setSReg_ID(String SReg_ID) {
        this.SReg_ID = SReg_ID;
    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getSReg_Date() {
        return SReg_Date;
    }

    public void setSReg_Date(String SReg_Date) {
        this.SReg_Date = SReg_Date;
    }
}
