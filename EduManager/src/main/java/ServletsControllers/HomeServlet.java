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


@WebServlet("/homepage")
public class HomeServlet extends HttpServlet {

    private  CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        try {
            courseDAO = new CourseDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses=courseDAO.findAll();
        req.setAttribute("courses",courses);
        req.getRequestDispatcher("homepage.jsp").forward(req,resp);
    }
}