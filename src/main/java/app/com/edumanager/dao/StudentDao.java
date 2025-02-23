package app.com.edumanager.dao;

import app.com.edumanager.models.Course;
import app.com.edumanager.models.Inscription;
import app.com.edumanager.models.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDao {
    private static Connection connection;

public StudentDao(){

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/edumanagedb", "root", "th3you78");

        if (this.connection == null) {
            throw new SQLException("Failed to establish database connection!");
        }

        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS student (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "birth_date INT NOT NULL" +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL, " +
                    ");";

            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'student' created successfully");
        }

    } catch (ClassNotFoundException e) {
        System.err.println("MySQL JDBC Driver not found!");
        e.printStackTrace();
    } catch (SQLException e) {
        System.err.println("Database connection error: " + e.getMessage());
        e.printStackTrace();
    }
}

    // create a student
    public static void createStudent(Student student, Course course) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "INSERT INTO student (name,email, birth_date) VALUES ( ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getBirthdate());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                Inscription inscription = new Inscription(student, course, 20250222);
                InscriptionDao inscriptionDao = new InscriptionDao();
                int id = rs.getInt(1);

                student.setId(id);

                inscriptionDao.insertInscription(inscription);
            }
        } catch (SQLException e) {
            System.err.println("Error inserting person: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // list all students
    public static List<Student> getAllStudents() {
        List<Student> personList = new ArrayList<>();
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return personList;
        }

        String query = "SELECT * FROM student";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getInt("birth_date"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                personList.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching persons: " + e.getMessage());
            e.printStackTrace();
        }
        return personList;
    }

    // get a person by git
    public static Student getStudentById(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return null;
        }

        Student student = null;
        String query = "SELECT * FROM student WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                     student  = new Student(
                            rs.getInt("id"),
                            rs.getInt("birth_date"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching person by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return student;
    }

    // update a person
    public static void updateStudent(Student student) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "UPDATE student SET birth_date = ?,name = ?,email = ?  WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, student.getBirthdate());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setInt(4, student.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating person: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // delete a person by id
    public static void deleteStudent(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting student : " + e.getMessage());
            e.printStackTrace();
        }
    }
}







