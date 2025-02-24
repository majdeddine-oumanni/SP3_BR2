package Dao;

import Dbutils.ConnectionDb;
import Models.Course;
import Models.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollementDAO {

    private Connection conn;

    public EnrollementDAO() throws SQLException, ClassNotFoundException {
        conn= ConnectionDb.getconnectiondb();
    }

    public List<Etudiant> getEtudiantsByCoursId(int coursId) {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT student.id, student.name, student.prenom,student.email,student.datenaissance FROM student INNER JOIN coursestudent ec ON student.id = ec.studentId WHERE ec.courseId = ?";

        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, coursId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    etudiants.add(new Etudiant(rs.getInt("id"),
                            rs.getString("name"),rs.getString("prenom"),
                            rs.getString("email"),rs.getString("datenaissance")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }


    public boolean enrollEtudiant(int etudiantId, int coursId) {
        String sql = "INSERT INTO coursestudent(courseId, studentId) VALUES (?, ?)";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, coursId);
            ps.setInt(2, etudiantId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unenrollEtudiant(int etudiantId, int coursId) {
        String sql = "DELETE FROM coursestudent WHERE courseId = ? AND studentId = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(2, etudiantId);
            ps.setInt(1, coursId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Course> getCoursByEtudiantId(int etudiantId) {
        List<Course> cours = new ArrayList<>();
        String sql = "SELECT c.title,c.description FROM course c JOIN coursestudent ec ON c.id = ec.courseId WHERE ec.studentId = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, etudiantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cours.add(new Course(rs.getString("title"),rs.getString("description")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cours;
    }
}