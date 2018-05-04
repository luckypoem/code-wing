package codewing.dao;

/**
 * This bean class is created by CodeWing(version: 1.0).
 * You can change the code generated by changing file 'bean.schema'.
 * This bean class is for database table named 'course'.
 */
public class Course implements java.io.Serializable {

    // All fields:
    //
    private Integer cid;
    private String name;
    private String lang;
    private Integer semester;
    private String teacher;
    private String info;

    /**
     * Empty Constructor for Course.
     */
    public Course() {

    }

    /**
     * Full Constructor for Course.
     */
    public Course(Integer cid,String name,String lang,Integer semester,String teacher,String info) {
        this.cid = cid;
        this.name = name;
        this.lang = lang;
        this.semester = semester;
        this.teacher = teacher;
        this.info = info;
    }

    // equals(Just Compare id):
    //
    @Override
    public boolean equals(Object obj) {
        return obj != null &&  obj instanceof Course && (this == obj ||
            this.cid.equals(((Course) obj).cid));
    }

    // toString Method
    //
    @Override
    public String toString() {
        return "Course {" +
            "cid: " + this.cid + ',' +
            "name: " + this.name + ',' +
            "lang: " + this.lang + ',' +
            "semester: " + this.semester + ',' +
            "teacher: " + this.teacher + ',' +
            "info: " + this.info + ',' +
            "}";
    }

    // Setter and Getter Method
    //
    public void setcid(Integer cid) {
        this.cid = cid;
    }

    public Integer getcid() {
        return this.cid;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getname() {
        return this.name;
    }

    public void setlang(String lang) {
        this.lang = lang;
    }

    public String getlang() {
        return this.lang;
    }

    public void setsemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getsemester() {
        return this.semester;
    }

    public void setteacher(String teacher) {
        this.teacher = teacher;
    }

    public String getteacher() {
        return this.teacher;
    }

    public void setinfo(String info) {
        this.info = info;
    }

    public String getinfo() {
        return this.info;
    }
}