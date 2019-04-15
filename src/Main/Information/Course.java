package Information;

public class Course {

    String Course_ID, Course_Sac, Course_Name;

    public Course(String Course_ID, String Course_Sac, String Course_Name){
        this.Course_ID= Course_ID;
        this.Course_Sac= Course_Sac;
        this.Course_Name=Course_Name;

    }

    public String getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(String course_ID) {
        Course_ID = course_ID;
    }

    public String getCourse_Sac() {
        return Course_Sac;
    }

    public void setCourse_Sac(String course_Sac) {
        Course_Sac = course_Sac;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }
}
