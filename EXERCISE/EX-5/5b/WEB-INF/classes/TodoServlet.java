package com.digitalnomads;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TodoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");

        HttpSession session = request.getSession();
        List<String> tasks = (List<String>) session.getAttribute("tasks");
        if (tasks == null) {
            tasks = new ArrayList<>();
            session.setAttribute("tasks", tasks);
        }

        tasks.add(task);

        response.sendRedirect("todo");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Digital Nomads To-Do List</title></head><body>");
        out.println("<h1>Digital Nomads: Your Travel Task List</h1>");
        out.println("<form action='todo' method='POST'>");
        out.println("<label for='task'>New Task (e.g., Find a co-working space, Plan next trip):</label>");
        out.println("<input type='text' id='task' name='task' required>");
        out.println("<input type='submit' value='Add Task'>");
        out.println("</form>");
        out.println("<hr>");
        out.println("<h2>Your To-Do List:</h2>");
        out.println("<ul>");

        HttpSession session = request.getSession();
        List<String> tasks = (List<String>) session.getAttribute("tasks");

        if (tasks != null) {
            for (String task : tasks) {
                out.println("<li>" + task + "</li>");
            }
        }

        out.println("</ul>");
        out.println("</body></html>");
    }
}
