package app.com.edumanager.dao;

import app.com.edumanager.models.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDao {
    private Connection connection;

public StudentDao(){

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/edumanagedb", "root", "");

        if (this.connection == null) {
            throw new SQLException("Failed to establish database connection!");
        }

        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS person (" +
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
    public void createStudent(Student student) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "INSERT INTO person (name,email, birthdate) VALUES ( ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getBirthdate());
            stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error inserting person: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // list all students
    public List<Student> getAllPersons() {
        List<Student> personList = new ArrayList<>();
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return personList;
        }

        String query = "SELECT * FROM person";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getInt("birthdate"),
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
    public Student getPersonById(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return null;
        }

        Student student = null;
        String query = "SELECT * FROM person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                     student  = new Student(
                            rs.getInt("id"),
                            rs.getInt("birthdate"),
                            rs.getString("name"),
                            rs.getString("birthdate")
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
    public void updatePerson(Student student) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "UPDATE person SET birthdate = ?,name = ?,email = ?  WHERE id = ?";
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
    public void deleteStudent (int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "DELETE FROM person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error deleting student : " + e.getMessage());
            e.printStackTrace();
        }
    }
}







