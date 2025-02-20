package app.com.edumanager.controllers;

import app.com.edumanager.dao.CourseDao;
import app.com.edumanager.dao.InscriptionDao;
import app.com.edumanager.models.Course;
import app.com.edumanager.models.Inscriptoin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/inscription/*")
public class InscriptionServlet extends HttpServlet {
    InscriptionDao inscriptionDao = new InscriptionDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String action = req.getPathInfo();

        switch (action){
            case "/delete":
                 deleteInscription(req, resp);
                  break;
            case "/list":
                listInscription(req, resp);
                break;
                  default:
                  break;
        }
    }

    private void listInscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Inscriptoin> inscription = inscriptionDao.getAllInscription();
        req.setAttribute("inscription", inscription);
        req.getRequestDispatcher("inscription.jsp").forward(req, resp);
    }

    private void deleteInscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String inscriptionId = req.getParameter("inscriptionId");

        try {
            inscriptionDao.deleteInscriptionById(Integer.parseInt(inscriptionId) );
            session.setAttribute("message", "Course deleted successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e){

            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }

        req.getRequestDispatcher("/inscription/list.jsp").forward(req, resp);    }



}
