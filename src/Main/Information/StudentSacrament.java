package Information;

public class StudentSacrament {

    String Sac_ID, Stu_ID, Sac_Date;

    public StudentSacrament(String Sac_ID, String Stu_ID, String Sac_Date){
        this.Sac_ID= Sac_ID;
        this.Stu_ID=Stu_ID;
        this.Sac_Date= Sac_Date;

    }

    public String getSac_ID() {
        return Sac_ID;
    }

    public void setSac_ID(String sac_ID) {
        Sac_ID = sac_ID;
    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getSac_Date() {
        return Sac_Date;
    }

    public void setSac_Date(String sac_Date) {
        Sac_Date = sac_Date;
    }
}
