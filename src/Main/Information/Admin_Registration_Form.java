package Information;

public class Admin_Registration_Form {
    String AReg_ID, Adm_ID, AReg_Date;

    public Admin_Registration_Form(String AReg_ID, String adm_ID, String AReg_Date) {
        this.AReg_ID = AReg_ID;
        Adm_ID = adm_ID;
        this.AReg_Date = AReg_Date;
    }

    public String getAReg_ID() {
        return AReg_ID;
    }

    public void setAReg_ID(String AReg_ID) {
        this.AReg_ID = AReg_ID;
    }

    public String getAdm_ID() {
        return Adm_ID;
    }

    public void setAdm_ID(String adm_ID) {
        Adm_ID = adm_ID;
    }

    public String getAReg_Date() {
        return AReg_Date;
    }

    public void setAReg_Date(String AReg_Date) {
        this.AReg_Date = AReg_Date;
    }
}
