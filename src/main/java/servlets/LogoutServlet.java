package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import model.*;

public class LogoutServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {
            response.setContentType("text/html");

            HttpSession session = request.getSession();

            session.invalidate();

            PrintWriter out=response.getWriter();
            out.println("<html><body>");

            request.getRequestDispatcher("link.html").include(request, response);
            out.println("You have successfully logged out!");

            out.println("</html></body>");
            out.close();
    }
}
