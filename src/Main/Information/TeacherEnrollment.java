package Information;

public class TeacherEnrollment {

    String Teach_ID, Course_ID, Teach_Enroll;

    public TeacherEnrollment(String Teach_ID, String Course_ID, String Teach_Enroll){
        this.Teach_ID=Teach_ID;
        this.Course_ID=Course_ID;
        this.Teach_Enroll=Teach_Enroll;

    }

    public String getTeach_ID() {
        return Teach_ID;
    }

    public void setTeach_ID(String teach_ID) {
        Teach_ID = teach_ID;
    }

    public String getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(String course_ID) {
        Course_ID = course_ID;
    }

    public String getTeach_Enroll() {
        return Teach_Enroll;
    }

    public void setTeach_Enroll(String teach_Enroll) {
        Teach_Enroll = teach_Enroll;
    }
}
