package com.digitalnomads;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class FeedbackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<String> feedbackList = (List<String>) session.getAttribute("feedbackList");
        if (feedbackList == null) {
            feedbackList = new ArrayList<>();
            session.setAttribute("feedbackList", feedbackList);
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String feedback = request.getParameter("feedback");

        String feedbackEntry = "<div style='border: 1px solid #00796b; padding: 10px; margin-bottom: 10px; border-radius: 5px; background: #e0f7fa;'>";
        feedbackEntry += "<strong>Name:</strong> " + name + "<br>";
        feedbackEntry += "<strong>Email:</strong> " + email + "<br>";
        feedbackEntry += "<strong>Feedback:</strong><br>" + feedback + "</div>";
        feedbackList.add(feedbackEntry);

        response.sendRedirect("feedback");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        List<String> feedbackList = (List<String>) session.getAttribute("feedbackList");

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head><title>Digital Nomads Feedback Submissions</title></head><body>");
        out.println("<h1>Submitted Feedback</h1>");
        if (feedbackList != null && !feedbackList.isEmpty()) {
            for (String feedback : feedbackList) {
                out.println(feedback);
            }
        } else {
            out.println("<p>No feedback submitted yet!</p>");
        }
        out.println("<a href='feedback.html'>Go Back to Feedback Form</a>");
        out.println("</body></html>");
    }
}
