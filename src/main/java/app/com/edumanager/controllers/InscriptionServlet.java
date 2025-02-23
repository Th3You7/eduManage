package app.com.edumanager.controllers;

import app.com.edumanager.dao.CourseDao;
import app.com.edumanager.dao.InscriptionDao;
import app.com.edumanager.models.Course;
import app.com.edumanager.models.Inscription;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/inscription/*")
public class InscriptionServlet extends HttpServlet {
    InscriptionDao inscriptionDao = new InscriptionDao();
    CourseDao courseDao = new CourseDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getPathInfo();

        switch (action){
            case "/delete":
                 deleteInscription(req, resp);
                  break;
            case "/list":
                listInscription(req, resp);
                break;
            case "/filter":
                filterByCourse(req, resp);
                  default:
                  break;
        }
    }

    private void listInscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Inscription> inscription = inscriptionDao.getAllInscription();
        List<Course> courses = courseDao.getAllCourses();
        req.setAttribute("inscriptions", inscription);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/WEB-INF/views/inscription/list.jsp").forward(req, resp);
    }

    private void deleteInscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String inscriptionId = req.getParameter("id");

        try {
            inscriptionDao.deleteInscriptionById(Integer.parseInt(inscriptionId) );
            session.setAttribute("message", "Course deleted successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e){

            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }

        req.getRequestDispatcher("/inscription/list.jsp").forward(req, resp);    }

    private void filterByCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int courseId = Integer.parseInt(req.getParameter("course"));
        List<Inscription> inscriptions = inscriptionDao.getAllInscriptionByCourse(courseId);

        req.setAttribute("inscriptions", inscriptions);
        req.setAttribute("courses", courseDao.getAllCourses());

        req.getRequestDispatcher("/WEB-INF/views/inscription/list.jsp").forward(req, resp);



    }

}
