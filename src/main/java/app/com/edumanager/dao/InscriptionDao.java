package app.com.edumanager.dao;

import app.com.edumanager.models.Course;
import app.com.edumanager.models.Inscriptoin;
import app.com.edumanager.models.Student;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscriptionDao {

    private static  final String HOST = "localhost";
    private static  final  int PORT = 3306;
    private static  final  String DB_NAME = "edumanagedb";
    private static  final  String USERNAME = "root";
    private static  final  String PASSWORD = "";

    private static  final  String DRIVER = "com.mysql.jdbc.Driver";
    private static  final  String INSERT = "insert into inscription (studentID, courseID, inscDate) values(?,?,?)";
    private static  final  String DELETE = "delete from inscription where id = ?";
    private static  final  String SELECT_ALL = "select * from inscription";
    private static  final  String SELECT_BY_ID = "select inscription.* , student.*, student.name as student_name, course.*, course.name as course_name " +
                                                "from inscription " +
                                                "inner join student on ?= student.id " +
                                                "inner join course on ? = course.id ";
    private static  final  String UPDATE = "update inscription set studentID = ?, courseID = ? where id = ?";

    private  static  final  String SELECT_ALL_BY_COURSE =  "select inscription.* , student.*, student.name as student_name, course.*, course.name as course_name " +
            "from inscription " +
            "inner join student on inscription.studentID = student.id " +
            "inner join course on inscription.courseID = course.id where course.id = ?";
    private  static  final  String SELECT_ALL_BY_STUDENT =  "select inscription.* , student.*, student.name as student_name, course.*, course.name as course_name " +
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
    public void insertInscription(Inscriptoin insc){
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, insc.getStudent().getId());
            preparedStatement.setInt(2, insc.getCourse().getId());;
            preparedStatement.setInt(3, insc.getInscDate());
            preparedStatement.executeQuery();
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    // get inscription
    public Inscriptoin getInscriptionById(int id){
        Inscriptoin inscription = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
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


                inscription = new Inscriptoin( student,course, inscDate);
            }
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

        return inscription;
    }

    // getAllInscriptions
    public List<Inscriptoin> getAllInscription(){
        List<Inscriptoin> inscriptions = new ArrayList<Inscriptoin>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int courseID = Integer.parseInt(rs.getString("courseID"));
                int studentID = Integer.parseInt(rs.getString("studentID"));
                int inscDate = Integer.parseInt(rs.getString("inscDate"));

                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("email");
                int studentdt = Integer.parseInt(rs.getString("birth_date"));
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("description");
                // create student
                Student student = new Student(studentID,studentdt,  studentEmail,studentName);

                // create course

                Course course = new Course(courseName,courseDescription);

                // create inscription
                Inscriptoin inscription = new Inscriptoin(student, course, inscDate);

                inscriptions.add(inscription);
            }

        }catch (SQLException e ){
            System.err.println("SQLException: " + e.getMessage());
        }

        return inscriptions;
}

    // updateInscription
    public void updateInscription(Inscriptoin insc){
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

    public List<Inscriptoin> getAllInscriptionByCourse(int courseID){
        List<Inscriptoin> inscriptions = new ArrayList<Inscriptoin>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_COURSE);
            preparedStatement.setInt(1, courseID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
               // int courseID = Integer.parseInt(rs.getString("courseID"));
                int studentID = Integer.parseInt(rs.getString("studentID"));
                int inscDate = Integer.parseInt(rs.getString("inscDate"));

                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("email");
                int studentdt = Integer.parseInt(rs.getString("birth_date"));
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("description");
                // create student
                Student student = new Student(studentID,studentdt,  studentEmail,studentName);

                // create course

                Course course = new Course(courseName,courseDescription);

                // create inscription
                Inscriptoin inscription = new Inscriptoin(student, course, inscDate);

                inscriptions.add(inscription);
            }

        }catch (SQLException e ){
            System.err.println("SQLException: " + e.getMessage());
        }

        return inscriptions;


    }
    public List<Inscriptoin> getAllInscriptionBystudent(int studentId){
        List<Inscriptoin> inscriptions = new ArrayList<Inscriptoin>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_STUDENT);
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int courseID = Integer.parseInt(rs.getString("courseID"));
                int studentID = Integer.parseInt(rs.getString("studentID"));
                int inscDate = Integer.parseInt(rs.getString("inscDate"));

                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("email");
                int studentdt = Integer.parseInt(rs.getString("birth_date"));
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("description");
                // create student
                Student student = new Student(studentID,studentdt,  studentEmail,studentName);

                // create course

                Course course = new Course(courseName,courseDescription);

                // create inscription
                Inscriptoin inscription = new Inscriptoin(student, course, inscDate);

                inscriptions.add(inscription);
            }

        }catch (SQLException e ){
            System.err.println("SQLException: " + e.getMessage());
        }

        return inscriptions;


    }

}
