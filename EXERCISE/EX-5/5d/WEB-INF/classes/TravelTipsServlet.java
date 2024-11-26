import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class TravelTipsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Welcome, " + username + "!</h1>");
        out.println("<h2>Your Digital Nomad Travel Tips</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DigitalNomads", "root", "password");

            String query = "SELECT tip FROM travel_tips";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            out.println("<ul>");
            while (rs.next()) {
                out.println("<li>" + rs.getString("tip") + "</li>");
            }
            out.println("</ul>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        out.println("<p><a href='login.html'>Logout</a></p>");
    }
}
