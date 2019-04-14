package report;


public class repo_haro_1 {

    String Teach_ID, Teach_FName, Teach_LName, Teach_Verify, Teach_Enroll, Course_Name;

    public repo_haro_1(String Teach_ID, String Teach_FName, String Teach_LName, String Teach_Verify, String Teach_Enroll, String Course_Name) {

        this.Teach_ID = Teach_ID;
        this.Teach_FName = Teach_FName;
        this.Teach_LName = Teach_LName;
        this.Teach_Verify = Teach_Verify;
        this.Teach_Enroll = Teach_Enroll;
        this.Course_Name = Course_Name;

    }

    public String getTeach_ID() {
        return Teach_ID;
    }

    public void setTeach_ID(String teach_ID) {
        Teach_ID = teach_ID;
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

    public String getTeach_Verify() {
        return Teach_Verify;
    }

    public void setTeach_Verify(String teach_Verify) {
        Teach_Verify = teach_Verify;
    }

    public String getTeach_Enroll() {
        return Teach_Enroll;
    }

    public void setTeach_Enroll(String teach_Enroll) {
        Teach_Enroll = teach_Enroll;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }
}