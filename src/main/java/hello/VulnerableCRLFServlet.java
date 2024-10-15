package hello;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VulnerableCRLFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = request.getParameter("cookieValue");

        // Create a cookie with the 'Secure' and 'HttpOnly' flags to avoid CWE-614
        javax.servlet.http.Cookie userCookie = new javax.servlet.http.Cookie("UserCookie", cookieValue);
        userCookie.setSecure(true);  // Prevent CWE-614 (cookie transmitted over non-HTTPS)
        userCookie.setHttpOnly(true);  // Adds HttpOnly flag for better security
        
        // Vulnerable to CWE-113: User input is directly added to the cookie value without sanitization
        response.addCookie(userCookie);
        response.getWriter().write("Cookie set with user input.");
    }
}