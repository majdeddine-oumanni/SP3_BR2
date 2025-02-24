package ServletsControllers;


import Dao.CourseDAO;
import Dao.EnrollementDAO;
import Dao.EtudiantsDao;
import Models.Course;
import Models.Etudiant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/enrollement")
public class EnrollementServlet extends HttpServlet {



    private  EnrollementDAO dao = new EnrollementDAO();
    private  CourseDAO courseDAO= new CourseDAO();
    private EtudiantsDao etudiantsDao = new EtudiantsDao();

    public EnrollementServlet() throws SQLException, ClassNotFoundException {

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action= req.getParameter("action");


        if (action==null){
            resp.sendRedirect("homepage");
        }
        if(action.equals("listetudiants")){
            int coursId= Integer.parseInt(req.getParameter("coursId"));
            req.setAttribute("course",courseDAO.findCourById(coursId));
            req.setAttribute("etudiants",dao.getEtudiantsByCoursId(coursId));
            req.getRequestDispatcher("/cours-students/list-etudiants-by-coursId.jsp").forward(req,resp);
        }
        else if(action.equals("listcours")){
            int etudiantId= Integer.parseInt(req.getParameter("etudiantId"));
            try {
                req.setAttribute("etudiant",etudiantsDao.getStudient(etudiantId));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("courses",dao.getCoursByEtudiantId(etudiantId));
            req.getRequestDispatcher("/cours-students/list-cours-by-etudiantId.jsp").forward(req,resp);
        }

        else if (action.equals("enroll"))
        {
            List<Etudiant> etudiants= null;
            try {
                etudiants = etudiantsDao.getStudients();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            List<Course> cours= null;
            cours = courseDAO.findAll();
            req.setAttribute("etudiants",etudiants);
            req.setAttribute("courses",cours);
            req.getRequestDispatcher("/cours-students/enroll.jsp").forward(req,resp);

        }

        resp.sendRedirect("homepage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws  IOException {

        String action = req.getParameter("action");
        int coursId = Integer.parseInt(req.getParameter("coursId"));
        int etudiantId = Integer.parseInt(req.getParameter("etudiantId"));

        if ("enroll".equalsIgnoreCase(action)) {
            System.out.println(coursId);
            System.out.println(etudiantId);
            dao.enrollEtudiant(etudiantId, coursId);
        } else if ("unenroll".equalsIgnoreCase(action)) {
            System.out.println(coursId);
            System.out.println(etudiantId);
            dao.unenrollEtudiant(etudiantId, coursId);
        }
        resp.sendRedirect("enrollement?coursId=" + coursId);
    }

}