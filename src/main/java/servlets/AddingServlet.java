package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import model.*;

public class AddingServlet extends HttpServlet {
    ArrayList<String> subjects = new ArrayList<>();
    public void init(ServletConfig config) {
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		subjects.clear();
	
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        String fileName = new String("/home/osboxes/IdeaProjects/AcademicLog/src/main/webapp");
        if( uri.equals("/AcademicLog/AddingServlet/add") ) {
			new ReaderWriter().readSubjects(fileName + "/subjects.txt", subjects);
			String name = request.getParameter("subjectName");
	    	if (!subjects.contains(name)){ 
				subjects.add(name);
				new ReaderWriter().writeSubjects(fileName + "/subjects.txt", subjects);
				ArrayList<LogEntry> tmp = new ArrayList<>();
				new XmlIO().saveItemsToFile(fileName + "/" + name	 + ".xml", tmp);
			}
	    	
	    	response.sendRedirect("/AcademicLog/sheet?SubjectName=" + name);	    
	    	return;
        }
		if( uri.equals("/AcademicLog/AddingServlet/cancel") ) {
	    	response.sendRedirect("/AcademicLog/MainPage");
	    	return;
        }
		PrintWriter out = response.getWriter();

        out.println("<html>\n<body>\n");
		request.getRequestDispatcher("auth_links.html").include(request, response); 
		out.println("<h2>Add a subject:</h2>");
        out.println(getMainPage());
        out.println("</body>\n</html>");
    }

    public String getMainPage() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<form method=\"GET\" action=\"/AcademicLog/AddingServlet/add\">\n");
        
		sb.append("Name of subject: <input type=\"text\" name=\"subjectName\">\n");  
        sb.append("<input type=\"submit\" value=\"Add\">\n");
        
		sb.append("</form>");

 		sb.append("<form method=\"GET\" action=\"/AcademicLog/AddingServlet/cancel\">\n");
		sb.append("<input type=\"submit\" value=\"Cancel\">\n");
        sb.append("</form>");
 
        return sb.toString();
    }
}
