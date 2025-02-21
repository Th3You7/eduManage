package app.com.edumanager.dao;

import app.com.edumanager.models.Course;

import java.sql.*;
import java.util.ArrayList;

public class CourseDao {
    private final String  jdbcUrl = "jdbc:mysql://localhost:3306/edumanagedb";
    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private final String username = "root";
    private final String password = "";

    private static final String SELECT_COURSE = "SELECT * FROM COURSE WHERE id=?";
    private static final String SELECT_ALL_COURSES = "SELECT * FROM COURSE";
    private static final String INSERT_COURSE = "INSERT INTO COURSE" + "  (name, `desc`) VALUES " + "( ?, ?);";
    private static final String DELETE_COURSE = "DELETE FROM COURSE WHERE id=?";
    private static final String UPDATE_COURSE = "UPDATE COURSE SET name = ?, `desc` = ? WHERE id = ?";


    //public EmployerDao() {}

    // Establish JDBC Connection
    protected Connection getConnection()  {
        Connection conn = null;
        try {
            // Load Type-4 Driver / MySQL Type-4 driver class
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to Database.");
            //Establish a connection
        }catch (SQLException e){
            System.err.println("SQL Error: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
        return conn;
    }

    //get a course
    public Course getCourse(int id){
        Course course = null;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_COURSE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int _id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String desc = rs.getString("desc");

                // create a course object and assign it
                 course = new Course(_id, name, desc);
            }
        }catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return course;
    }
    //add a course
    public void addCourse(Course course) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_COURSE);
            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.executeUpdate();
            System.out.println("Course added.");
        }catch (SQLException e){
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
    //list all courses
    public ArrayList<Course> getAllCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_COURSES);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int _id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String desc = rs.getString("desc");
                courses.add(new Course(_id, name, desc));
            }
        }catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return courses;
    }
    //edit a course
    public void updateCourse(Course course) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_COURSE);
            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setInt(3, course.getId());
            ps.executeUpdate();
        }catch(SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
    //delete a course
    public void deleteCourse(int id) {
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_COURSE);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

}
