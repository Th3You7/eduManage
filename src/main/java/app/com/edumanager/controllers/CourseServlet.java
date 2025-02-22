package app.com.edumanager.controllers;

import app.com.edumanager.dao.CourseDao;
import app.com.edumanager.models.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/course/*")
public class CourseServlet extends HttpServlet {
    CourseDao courseDao = new CourseDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // if no path info we will redirect to list page
        String action = req.getPathInfo();

        switch (action){
            case "/new":
                addCourse(req, resp);
                break;
            case "/new-form":
                addCourseForm(req, resp);
                break;
            case "/edit":
                editCourse(req, resp);
                break;
            case "/edit-form":
                editCourseForm(req, resp);
                break;
            case "/delete":
                deleteCourse(req, resp);
                break;
            case "/list":
                listCourses(req, resp);
                break;
            default:
                resp.sendRedirect("/course/list");
        }
    }

   private void addCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Course course = new Course(name, description);
         HttpSession session = req.getSession();
        try {
            courseDao.addCourse(course);
            session.setAttribute("message", "Course added successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e){
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("course/list");
    }
    private void addCourseForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/course/form.jsp").forward(req, resp);
    }
    private void editCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        HttpSession session = req.getSession();

        try {
            Course course = courseDao.getCourse(id);
            course.setName(name);
            course.setDescription(description);
            courseDao.updateCourse(course);
            session.setAttribute("message", "Course edited successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e){
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }

        resp.sendRedirect("course/list");

    }
    private void editCourseForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Course course = courseDao.getCourse(Integer.parseInt(id));
        req.setAttribute("course", course);
        req.getRequestDispatcher("/WEB-INF/views/course/form.jsp").forward(req, resp);
    }
    private void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String courseId = req.getParameter("id");
        try {
            courseDao.deleteCourse(Integer.parseInt(courseId));
            session.setAttribute("message", "Course deleted successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e){
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("course/list");
    }
    private void listCourses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Course> courses = courseDao.getAllCourses();
        req.setAttribute("courses", courses);

        req.getRequestDispatcher("/WEB-INF/views/course/list.jsp").forward(req, resp);
    }
}


