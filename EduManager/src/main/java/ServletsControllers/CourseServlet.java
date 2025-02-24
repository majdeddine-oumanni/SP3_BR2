package ServletsControllers;

import Dao.CourseDAO;
import Models.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        try {
            courseDAO = new CourseDAO();
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            try {
                afficher(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        switch (action) {
            case "add":
                ajouter(req, resp);
                break;
            case "update":
                try {
                    modifier(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    supprimer(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            default:
                try {
                    afficher(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        }
    }


    private void afficher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        List<Course> courses = courseDAO.findAll();
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/course/index.jsp").forward(req, resp);
    }


    private void supprimer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        courseDAO.delete(id);
        resp.sendRedirect("courses");

    }

    private void modifier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(req.getParameter("id"));
        Course course = courseDAO.findCourById(id);
        req.setAttribute("course", course);
        req.getRequestDispatcher("/course/edit.jsp").forward(req, resp);
    }

    private void ajouter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/course/add.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String title = req.getParameter("title");
        String description= req.getParameter("description");

        String action = req.getParameter("action");
        Course course = new Course(title, description);

        if (action.contains("ajouter")) {
            courseDAO.save(course);
            resp.sendRedirect("courses");
        }

        if (action.contains("modifier")) {
            int id=Integer.parseInt(req.getParameter("id"));
            course.setId(id);
            courseDAO.update(course);
            resp.sendRedirect("courses");
        }

    }
}
