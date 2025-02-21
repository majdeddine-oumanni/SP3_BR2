package DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutil {
    static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("DriverManager");
        String url = "jdbc:mysql://localhost:3307/etudiantdata";
        con = DriverManager.getConnection(url , "root","admin0000");
        return con;
    }
}