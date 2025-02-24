package Dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {


    public static Connection  getconnectiondb() throws SQLException, ClassNotFoundException {



        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/studentmanagement","root","amdin0000");

        Statement stm=connection.createStatement();


        String courseTable="CREATE TABLE IF NOT EXISTS course(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "title VARCHAR(20)," +
                "description VARCHAR(200))";


        stm.executeUpdate(courseTable);

        //create table student

        String studentTable="CREATE TABLE IF NOT EXISTS student(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(20)," +
                "prenom VARCHAR(200)," +
                "email VARCHAR(120)," +
                "datenaissance VARCHAR(30))";

        stm.executeUpdate(studentTable);


        String coursestudent="CREATE TABLE IF NOT EXISTS coursestudent(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "courseId INT," +
                "studentId INT," +
                "FOREIGN KEY (courseId) REFERENCES course(id)," +
                "FOREIGN KEY (studentId) REFERENCES student(id))";


        stm.executeUpdate(coursestudent);


        return connection;
    }


}