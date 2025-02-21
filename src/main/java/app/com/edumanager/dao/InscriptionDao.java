package app.com.edumanager.dao;

import app.com.edumanager.models.Inscriptoin;

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
    private static  final  String SELECT_BY_ID = "select * from inscription where id = ?";
    private static  final  String UPDATE = "update inscription set studentID = ?, courseID = ? where id = ?";



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

                inscription = new Inscriptoin(courseID, studentID, inscDate);
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
                // create student

                // create course

                // create inscription
                Inscriptoin inscription = new Inscriptoin(courseID, studentID, inscDate);
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


}
