package app.com.edumanager.models;

public class Inscription {
    private int id;
    private Student student;
    private Course course;
    private int inscDate;

    public Inscription(Student student, Course course, int inscDate) {

        this.course = course;
        this.student = student;
        this.inscDate = inscDate;
    }

    public Inscription(int id, Student student, Course course, int inscDate) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.inscDate = inscDate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
   }

    public Course getCourse() {
        return course;
   }

    public int getInscDate() {
        return inscDate;
    }
   public void setInscDate(int inscDate) {
        this.inscDate = inscDate;
   }

    @Override
    public String toString() {
        return "Inscriptin{" +
                "id=" + id +
                ", student_id=" +student +
                ", course_id=" + course+
                ", start_date=" + inscDate +
                '}';
    }
}
