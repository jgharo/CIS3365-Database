package Information;

public class ParentRegistrationForm {

    String PReg_ID,Par_ID, PReg_Date;

    public ParentRegistrationForm(String PReg_ID, String Par_ID, String PReg_Date){
        this.PReg_ID=PReg_ID;
        this.Par_ID=Par_ID;
        this.PReg_Date=PReg_Date;

    }

    public String getPReg_ID() {
        return PReg_ID;
    }

    public void setPReg_ID(String PReg_ID) {
        this.PReg_ID = PReg_ID;
    }

    public String getPar_ID() {
        return Par_ID;
    }

    public void setPar_ID(String par_ID) {
        Par_ID = par_ID;
    }

    public String getPReg_Date() {
        return PReg_Date;
    }

    public void setPReg_Date(String PReg_Date) {
        this.PReg_Date = PReg_Date;
    }
}
