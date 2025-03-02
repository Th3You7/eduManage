package app.com.edumanager.dao;

import app.com.edumanager.enums.Role;
import app.com.edumanager.models.User;

import java.sql.*;

public class UserDao {
    private static  final String HOST = "localhost";
    private static  final  int PORT = 3306;
    private static  final  String DB_NAME = "edumanagedb";
    private static  final  String USERNAME = "root";
    private static  final  String PASSWORD = "th3you78";

    private static  final  String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static  final String SELECT_USER = "select user.*, role.name role " +
            "from user " +
            "inner join role on role.id = user.role " +
            "where user.email = ? AND user.password = ?";
    private static  final String INSERT_USER = "insert into user values(?,?,?,?)";
    private static  final String UPDATE_USER = "update user set password = ? where email = ?";
    private static  final String DELETE_USER = "delete from user where email = ?";


    public Connection getConnection(){
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


    public User getUser(User user)  {
        User user1 = null;
        try {
            System.out.println("Hello");
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Hello 2");

            while (resultSet.next()) {
               int userId = resultSet.getInt(1);
               String name = resultSet.getString(2);
               String email = resultSet.getString(3);
               String password = resultSet.getString(4);
               Role role = Role.ADMIN;
               user1 = new User(userId,name,email,password,role);
            }
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

        return user1;
    }

}
