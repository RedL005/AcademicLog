

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.TreeMap;
import model.*;

public class SheetServlet extends HttpServlet {
    ArrayList<LogEntry> entries = new ArrayList<LogEntry>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
	
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        HttpSession session = request.getSession(false);
	

        if(session != null){
	    	request.getRequestDispatcher("auth_links.html").include(request, response);  
			String name = (String)session.getAttribute("name");    
  	    	out.println("Hello, " + name);
        }
        else{
            request.getRequestDispatcher("link.html").include(request, response);
        }

        out.println("<link rel=\"stylesheet\" href=\"table.css\"/>");
		
		String query = request.getQueryString();
		String subject = query.substring(query.lastIndexOf("=") + 1);
		//String homeDir = System.getProperty("user.home");
        String fileName = new String("/home/osboxes/IdeaProjects/AcademicLog/src/main/webapp/" + subject + ".xml");
		String logs = new String("/home/osboxes/IdeaProjects/AcademicLog/src/main/webapp/subjects.txt");
		String save = new String("/home/osboxes/IdeaProjects/AcademicLog/src/main/webapp/saved.xml");
		new XmlIO().loadItemsFromFile(fileName, entries);

		out.println("<form method = \"get\" action = \"sheet\">\n");
        out.println("<select name = \"SubjectSelect\" onchange=\"this.form.submit()\">\n");
		ArrayList<String> subjects = new ArrayList<>();
		new ReaderWriter().readSubjects(logs, subjects); 
		for (String cur : subjects){
			out.println("<option value=\"" + cur + "\" ");
			if (cur.equals(subject)) {out.println("selected");}
			out.println(">" + cur + "</option>\n");
		}

        out.println("</select></form>");

		out.println("<form method=\"GET\" action=\"/AcademicLog/SavingServlet\"> ");
		out.println("<table>");

		int maxLR = 0;
		for (LogEntry cur : entries){
			maxLR = Math.max(maxLR, cur.getLR().size());
		}
		int maxKR = 0;
		for (LogEntry cur : entries){
			maxKR = Math.max(maxKR, cur.getKR().size());
		}
		String isRead = "";
		if (session == null) isRead = "readonly";

		out.print("<tr> <th>Name</th>");
		for(int i = 0; i < maxLR; i++)
			out.print("<th>LR" + (i + 1) + "</th>");
		for(int i = 0; i < maxKR; i++)
			out.print("<th>KR" + (i + 1) + "</th>");

		out.println("<th>CW</th><th>Exam</th><th>Total Score</th></tr>");

		
        for(int i = 0; i < entries.size(); i++){
			out.println("<tr>");

			out.println("<td><input type=\"text\" name=\"name\" readonly value=\"" +
					entries.get(i).getName() + "\"></td>");
			
			ArrayList<Character> lrList = entries.get(i).getLR();
			for (int j = 0; j < maxLR; j++){
				out.print("<td><input type=\"text\" name=\"LR" + j + "\" ");

				if ( j < lrList.size())
					out.println("value=\"" + lrList.get(j) + "\" " + isRead + "></td>");

				else out.println("value=\"0\" " + isRead + "></td>");
				
			}
			out.print("\n");

			ArrayList<Character> krList = entries.get(i).getKR();
			for (int j = 0; j < maxKR; j++){
				out.print("<td><input type=\"text\" name=\"KR" + j + "\" ");

				if ( j < krList.size())
					out.println("value=\"" + krList.get(j) + "\" " + isRead + "></td>");

				else out.println("value=\"0\" " + isRead + "></td>");
				
			}
			out.print("\n");


			out.print("<td><input type=\"text\" name=\"CW\" ");

			if (entries.get(i).getCW().size() == 1)
				out.println("value=\"" + entries.get(i).getCW().get(0) + "\" " + isRead + "></td>");

			else out.print("value=\"0\" " + isRead + "></td>");

			out.print("<td><input type=\"text\" name=\"Exam\" ");
			if (entries.get(i).getExam().size() == 1)
				out.println("value=\"" + entries.get(i).getExam().get(0) + "\" " + isRead + "></td>");

			else out.print("value=\"0\" " + isRead + "></td>");

			out.println("<td>" + entries.get(i).getTotal() + "</td>");

			out.println("</tr>");
		}

		out.println("</table>");

        if (session != null){
			out.println("<center><input type=\"submit\" value=\"Submit\"></center>");
		}
		out.println("</form>");



        out.println("</body></html>");
		new XmlIO().saveItemsToFile(save, entries);
        out.close();
		entries.clear();
    }
}
