import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.TreeMap;
import model.*;

public class SavingServlet extends HttpServlet {
    ArrayList<LogEntry> entries = new ArrayList<LogEntry>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        HttpSession session = request.getSession(false);

        String[] values = request.getParameterValues("LR1");

        for (int i = 0; i < values.length; i++) {
            out.println("value " + i + " = " + values[i]);
        }
    }
}