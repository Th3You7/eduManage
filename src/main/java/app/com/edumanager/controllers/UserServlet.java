package app.com.edumanager.controllers;

import app.com.edumanager.dao.UserDao;
import app.com.edumanager.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        switch (action){
            case "/login":
                login(req, resp);
                break;
            case "/login-page":
                loginForm(req, resp);
                break;
            case "/register-page":
                registerPage(req, resp);
                break;
            case "/logout":
               logout(req, resp);
                break;
            default:
                resp.sendRedirect("/login-page");
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        try {
            User user = userDao.getUser(new User(email, password));
            session.setAttribute("user", user);
            resp.sendRedirect("/student/list");
        }catch (Exception e){
            session.setAttribute("message", "Email or password incorrect");
            session.setAttribute("messageType", "danger");
            resp.sendRedirect("/user/login-page");
        }
    }
    public void loginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
    }
    public void registerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp");
    }
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("/user/login-page");

    }


}
