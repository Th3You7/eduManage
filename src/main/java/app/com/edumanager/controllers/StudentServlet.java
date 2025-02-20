package app.com.edumanager.controllers;


import app.com.edumanager.dao.StudentDao;
import app.com.edumanager.models.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;

@WebServlet("/student/*")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String action = req.getPathInfo();
        System.out.println(action);

        switch (action) {
            case "/new":
                createStudent(req, resp);
                break;
            case "/new-form":
                addStudentForm(req, resp);
                break;
            case "/edit":
                updateStudent(req, resp);
                break;
            case "/edit-form":
                updateStudentForm(req, resp);
                break;
            case "/delete":
                deleteStudent(req, resp);
                break;
            case "/list":
                listStudent(req, resp);
                break;
            default:
                resp.sendRedirect("/student/list");
        }
    }


    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int birthdate = Integer.parseInt(req.getParameter("birthdate"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Student student = new Student(birthdate, name, email);
        HttpSession session = req.getSession();
        try {
            StudentDao.createStudent(student);
            session.setAttribute("message", "Student added successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e){
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("student/list");
    }
    private void addStudentForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/student/form.jsp").forward(req, resp);
    }
    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int birthdate = Integer.parseInt(req.getParameter("birthdate"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();

        try {
            Student student = StudentDao.getStudentById(id);
            student.setBirthdate(birthdate);
            student.setName(name);
            student.setEmail(email);
            StudentDao.updateStudent(student);
            session.setAttribute("message", "Course edited successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e){
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }

        resp.sendRedirect("/student/list");

    }
    private void updateStudentForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
       Student student = StudentDao.getStudentById(Integer.parseInt(id));
        req.setAttribute("student", student);
        req.getRequestDispatcher("student-form.jsp").forward(req, resp);
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String courseId = req.getParameter("courseId");
        try {
            StudentDao.deleteStudent(Integer.parseInt(courseId));
            session.setAttribute("message", "Course deleted successfully");
            session.setAttribute("messageType", "success");
        }catch (Exception e){
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/course/list");
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students= StudentDao.getAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
    }
}
