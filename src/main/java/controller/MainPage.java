package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.TreeMap;
import model.*;

public class MainPage extends HttpServlet {
    ArrayList<String> subjects = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
	
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        HttpSession session = request.getSession(false);
		out.println("<center>");
	

        if(session != null){
	    	request.getRequestDispatcher("auth_links.html").include(request, response);  
			String name = (String)session.getAttribute("name");    
  	    	out.println("<h2>Hello, " + name + "</h2>");
        }
        else{
            request.getRequestDispatcher("link.html").include(request, response);
			out.println("<h2>Welcome! Please, choose a sheet to browse or log in</h2>");
        }

        try {
			//String homeDir = System.getProperty("user.home");
			String fileName = new String("/home/osboxes/IdeaProjects/AcademicLog/src/main/webapp/subjects.txt");
			//String save = new String(homeDir + "/Documents/TOMCAT/tomcat/webapps/AcademicLog/saved.txt");
			new ReaderWriter().readSubjects(fileName, subjects);
		}
		catch(IOException e){
        	out.println(e.getMessage());
		}
	
		out.println("<h3>Subjects</h3>\n");

		out.println("<form method = \"get\" action = \"sheet\">\n");
        out.println("<select name = \"SubjectSelect\" onsubmit=\"this.form.submit()\">\n");
		//out.println("<option style=\"display:none\" selected></option>");
		for (String subject : subjects){
			out.println("<option value=\"" + subject + "\">" + subject + "</option>\n");
		}
        out.println("</select>");
		out.println("<input type = \"submit\" value = \"Select\"></form>");
		out.println("</center>");

        out.println("</body></html>");
		//new ReaderWriter().writeSubjects(save, subjects);
        out.close();
		subjects.clear();
    }
}
