package app.com.edumanager.dao;

import app.com.edumanager.models.Course;
import app.com.edumanager.models.Inscription;
import app.com.edumanager.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscriptionDao {

    private static  final String HOST = "localhost";
    private static  final  int PORT = 3306;
    private static  final  String DB_NAME = "edumanagedb";
    private static  final  String USERNAME = "root";
    private static  final  String PASSWORD = "th3you78";

    private static  final  String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static  final  String INSERT = "insert into inscription (studentID, courseID, insc_date) VALUES (?,?,?)";
    private static  final  String DELETE = "delete from inscription where id = ?";
    private static  final  String SELECT_ALL = "select inscription.* , student.*, student.name as student_name, course.*, course.name as course_name, inscription.id as inscription_id " +
                                            "from inscription " +
                                            "inner join student on inscription.studentID = student.id " +
                                            "inner join course on inscription.courseID = course.id ";
    private static  final  String SELECT_BY_ID = "select inscription.* , student.*, student.name as student_name, course.*, course.name as course_name, inscription.id as inscription_id " +
                                                "from inscription " +
                                                "inner join student on ?= student.id " +
                                                "inner join course on ? = course.id ";
    private static  final  String UPDATE = "update inscription set studentID = ?, courseID = ? where id = ?";

    private  static  final  String SELECT_ALL_BY_COURSE =  "select inscription.* , student.*, student.name as student_name, course.*, course.name as course_name, inscription.id as inscription_id " +
            "from inscription " +
            "inner join student on inscription.studentID = student.id " +
            "inner join course on inscription.courseID = course.id where course.id = ?";
    private  static  final  String SELECT_ALL_BY_STUDENT =  "select inscription.* , student.*, student.name as student_name, course.*, course.name as course_name, inscription.id as inscription_id " +
            "from inscription " +
            "inner join student on inscription.studentID = student.id " +
            "inner join course on inscription.courseID = course.id where student.id = ?";




    //private static Connection connection;


    public  Connection getConnection(){
         Connection connection = null;
        try {
        Class.forName(DRIVER);
            connection = DriverManager.getConnection(String. format("jdbc:mysql://%s:%d/%s",HOST, PORT,DB_NAME),USERNAME,PASSWORD);
        }catch (SQLException se){
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

   // ========================================================================================



    // add Inscription
    public void insertInscription(Inscription insc){
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, insc.getStudent().getId());
            preparedStatement.setInt(2, insc.getCourse().getId());;
            preparedStatement.setInt(3, insc.getInscDate());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    // get inscription
    public Inscription getInscriptionById(int id){
        Inscription inscription = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int courseID = Integer.parseInt(rs.getString("courseID"));
                int studentID = Integer.parseInt(rs.getString("studentID"));
                int inscDate = Integer.parseInt(rs.getString("inscDate"));

                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("email");
                int studentdt = Integer.parseInt(rs.getString("studentdt"));
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("description");

                Course course = new Course(courseName, courseDescription);
                Student student = new Student(studentID,studentdt,  studentEmail,studentName);


                inscription = new Inscription( student,course, inscDate);
            }
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

        return inscription;
    }

    // getAllInscriptions
    public List<Inscription> getAllInscription(){
        List<Inscription> inscriptions = new ArrayList<Inscription>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int insciptionID = Integer.parseInt(rs.getString("inscription_id"));
                int studentID = Integer.parseInt(rs.getString("studentID"));
                int inscDate = Integer.parseInt(rs.getString("insc_date"));

                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("email");
                int studentBirthDate = Integer.parseInt(rs.getString("birth_date"));
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("desc");
                // create student
                Student student = new Student(studentID,studentBirthDate, studentName,studentEmail);

                // create course

                Course course = new Course(courseName,courseDescription);

                // create inscription
                Inscription inscription = new Inscription(insciptionID, student, course, inscDate);

                inscriptions.add(inscription);
            }

        }catch (SQLException e ){
            System.err.println("SQLException: " + e.getMessage());
        }

        return inscriptions;
}

    // updateInscription
    public void updateInscription(Inscription insc){
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, insc.getStudent().getId());
            ps.setInt(2, insc.getCourse().getId());
            ps.setInt(3, insc.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }

    // deleteInscription
    public void deleteInscriptionById(int id) {

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

    }

    public List<Inscription> getAllInscriptionByCourse(int courseID){
        List<Inscription> inscriptions = new ArrayList<Inscription>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_COURSE);
            preparedStatement.setInt(1, courseID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
               // int courseID = Integer.parseInt(rs.getString("courseID"));
                int insciptionID = Integer.parseInt(rs.getString("inscription_id"));
                int studentID = Integer.parseInt(rs.getString("studentID"));
                int inscDate = Integer.parseInt(rs.getString("insc_date"));

                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("email");
                int studentdt = Integer.parseInt(rs.getString("birth_date"));
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("desc");
                // create student
                Student student = new Student(studentID,studentdt,  studentName, studentEmail);

                // create course

                Course course = new Course(courseName,courseDescription);

                // create inscription
                Inscription inscription = new Inscription(insciptionID, student, course, inscDate);

                inscriptions.add(inscription);
            }

        }catch (SQLException e ){
            System.err.println("SQLException: " + e.getMessage());
        }

        return inscriptions;


    }

    public List<Inscription> getAllInscriptionBystudent(int studentId){
        List<Inscription> inscriptions = new ArrayList<Inscription>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_STUDENT);
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){

                int insciptionID = Integer.parseInt(rs.getString("inscription_id"));
                int studentID = Integer.parseInt(rs.getString("studentID"));
                int inscDate = Integer.parseInt(rs.getString("insc_date"));

                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("email");
                int studentdt = Integer.parseInt(rs.getString("birth_date"));
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("desc");
                // create student
                Student student = new Student(studentID,studentdt,studentName, studentEmail);

                // create course

                Course course = new Course(courseName,courseDescription);

                // create inscription
                Inscription inscription = new Inscription(insciptionID,student, course, inscDate);

                inscriptions.add(inscription);
            }

        }catch (SQLException e ){
            System.err.println("SQLException: " + e.getMessage());
        }

        return inscriptions;


    }

}
