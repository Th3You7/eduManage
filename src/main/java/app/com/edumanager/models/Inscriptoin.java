package app.com.edumanager.models;

import java.util.Date;

public class Inscriptoin {
    private int id;
    private Student student;
    private Course course;
    private int inscDate;

    public Inscriptoin(Student student, Course course, int inscDate) {

        this.course = course;
        this.student = student;
        this.inscDate = inscDate;
    }

    public Inscriptoin(int id, Student student, Course course, int inscDate) {
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

   public void setStudentId(int studentId) {
        this.student = student;
   }
   public Course getCourse() {
        return course;
   }
   public void setCourseId(int courseId) {
        this.course = course;
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
