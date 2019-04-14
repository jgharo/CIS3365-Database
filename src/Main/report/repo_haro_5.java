package report;


public class repo_haro_5 {

    String Class_ID, Class_Name, Class_Lang, Teach_FName, Teach_LName;

    public repo_haro_5 (String Class_ID, String Class_Name, String Class_Lang, String Teach_FName, String Teach_LName) {
        this.Class_ID = Class_ID;
        this.Class_Name= Class_Name;
        this.Class_Lang= Class_Lang;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;


    }

    public String getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(String class_ID) {
        Class_ID = class_ID;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public void setClass_Name(String class_Name) {
        Class_Name = class_Name;
    }

    public String getClass_Lang() {
        return Class_Lang;
    }

    public void setClass_Lang(String class_Lang) {
        Class_Lang = class_Lang;
    }

    public String getTeach_FName() {
        return Teach_FName;
    }

    public void setTeach_FName(String teach_FName) {
        Teach_FName = teach_FName;
    }

    public String getTeach_LName() {
        return Teach_LName;
    }

    public void setTeach_LName(String teach_LName) {
        Teach_LName = teach_LName;
    }
}