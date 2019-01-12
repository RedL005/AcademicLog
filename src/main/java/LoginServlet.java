

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.TreeMap;
import java.util.Random;
import java.nio.charset.Charset;
import model.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        TreeMap<String,String> map = new TreeMap<String, String>();
		String homeDir = System.getProperty("user.home");
        String fileName = new String(homeDir + "/Documents/TOMCAT/tomcat/webapps/AcademicLog/users.txt");
		new ReaderWriter().read(fileName, map);

		if(map.get(name) == null){
	    	request.getRequestDispatcher("link.html").include(request, response);
            out.println("Error: There's no user with this username!");
            request.getRequestDispatcher("login.html").include(request, response);	
		}

        else if(password.equals(map.get(name)) ){
			request.getRequestDispatcher("auth_links.html").include(request, response);
        	out.print("Welcome, " + name);
        	HttpSession session = request.getSession();
        	session.setAttribute("name", name);
	    	new ReaderWriter().write("users.txt", map);
        }

        else{
	    	request.getRequestDispatcher("link.html").include(request, response);
            out.print("Error: Password is incorrect!");
            request.getRequestDispatcher("login.html").include(request, response);
        }

        out.println("</html></body>");
        out.close();
    }


    private String generateRandomID(){
	byte[] array = new byte[13];
	new Random().nextBytes(array);
	String result = new String(array, Charset.forName("UTF-8"));
	return result;
    }

}
