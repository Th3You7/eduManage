package app.com.edumanager.models;

import java.util.Date;

public class Inscriptoin {
    private int id;
    private int studentId;
    private int courseId;
    private int inscDate;

    public Inscriptoin() {

    }

    public Inscriptoin(int id, int studentId, int courseId, int inscDate) {
        this.id = id;
        this.courseId = studentId;
        this.studentId = courseId;
        this.inscDate = inscDate;
    }

   public Inscriptoin(int studentId, int courseId, int inscDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.inscDate = inscDate;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public int getStudentId() {
        return studentId;
   }

   public void setStudentId(int studentId) {
        this.studentId = studentId;
   }
   public int getCourseId() {
        return courseId;
   }
   public void setCourseId(int courseId) {
        this.courseId = courseId;
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
                ", student_id=" +studentId +
                ", course_id=" + courseId +
                ", start_date=" + inscDate +
                '}';
    }
}
