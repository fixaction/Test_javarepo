package hello;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.encoder.Encode;

public class VulnerableXSSServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get 'name' parameter from request, which can be user-controlled input
        String name = request.getParameter("name");

        // Vulnerable: User input is directly outputted to the page without sanitization
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Welcome, " + Encode.forHtml(name) + "!</h1>"); // XSS vulnerability
        out.println("</body></html>");
    }
}