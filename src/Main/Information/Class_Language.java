package Information;

public class Class_Language {
    String ClassLang_ID, Class_ID, Class_Lang;

    public Class_Language(String ClassLang_ID, String Class_ID, String Class_Lang) {
        this.ClassLang_ID = ClassLang_ID;
        this.Class_ID = Class_ID;
        this.Class_Lang = Class_Lang;
    }

    public String getClassLang_ID() {
        return ClassLang_ID;
    }

    public void setClassLang_ID(String classLang_ID) {
        ClassLang_ID = classLang_ID;
    }

    public String getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(String class_ID) {
        Class_ID = class_ID;
    }

    public String getClass_Lang() {
        return Class_Lang;
    }

    public void setClass_Lang(String class_Lang) {
        Class_Lang = class_Lang;
    }
}
