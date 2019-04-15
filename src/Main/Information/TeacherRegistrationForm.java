package Information;

public class TeacherRegistrationForm {

    String TReg_ID,Teach_ID, TReg_Date;

    public TeacherRegistrationForm(String TReg_ID, String Teach_ID, String TReg_Date){
        this.TReg_ID=TReg_ID;
        this.Teach_ID=Teach_ID;
        this.TReg_Date=TReg_Date;

    }

    public String getTReg_ID() {
        return TReg_ID;
    }

    public void setTReg_ID(String TReg_ID) {
        this.TReg_ID = TReg_ID;
    }

    public String getTeach_ID() {
        return Teach_ID;
    }

    public void setTeach_ID(String teach_ID) {
        Teach_ID = teach_ID;
    }

    public String getTReg_Date() {
        return TReg_Date;
    }

    public void setTReg_Date(String TReg_Date) {
        this.TReg_Date = TReg_Date;
    }
}
