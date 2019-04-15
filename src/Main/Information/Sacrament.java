package Information;

public class Sacrament {

    String Sac_ID, Sac_Type;

    public Sacrament(String Sac_ID, String Sac_Type){
        this.Sac_ID= Sac_ID;
        this.Sac_Type= Sac_Type;

    }

    public String getSac_ID() {
        return Sac_ID;
    }

    public void setSac_ID(String sac_ID) {
        Sac_ID = sac_ID;
    }

    public String getSac_Type() {
        return Sac_Type;
    }

    public void setSac_Type(String sac_Type) {
        Sac_Type = sac_Type;
    }
}
