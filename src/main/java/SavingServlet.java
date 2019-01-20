import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.TreeMap;
import model.*;

public class SavingServlet extends HttpServlet {
    ArrayList<LogEntry> entries;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        entries = new ArrayList<LogEntry>();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        HttpSession session = request.getSession(false);

        String[] names = request.getParameterValues("name");
        String url = request.getHeader("referer");
        String subject = url.substring(url.indexOf("=") + 1);

        for (int i = 0; i < names.length; i++) {

            LogEntry newEntry = new LogEntry(names[i]);

            String[] LR = request.getParameterValues("LR0");
            for (int j = 1; LR != null; j++){
                if (LR[i].length() > 0)
                    newEntry.addLR(LR[i].charAt(0));
                else
                    newEntry.addLR('0');
                LR = request.getParameterValues("LR" + j);
            }

            String[] KR = request.getParameterValues("KR0");
            for (int j = 1; KR != null; j++){
                if (KR[i].length() > 0)
                    newEntry.addKR(KR[i].charAt(0));
                else
                    newEntry.addKR('0');
                KR = request.getParameterValues("KR" + j);
            }

            String[] CW = request.getParameterValues("CW");

            if (CW != null)
                newEntry.addCW(CW[i].charAt(0));
//            else
//                newEntry.addCW('0');


            String[] exam = request.getParameterValues("Exam");
            for (int j = 1; exam != null; j++){
                if (exam[i].length() > 0)
                    newEntry.addExam(exam[i].charAt(0));
                else
                    newEntry.addExam('0');
                exam = request.getParameterValues("Exam" + j);
            }

            entries.add(newEntry);
        }

        String fileName = new String("/home/osboxes/IdeaProjects/AcademicLog/src/main/webapp");


        new XmlIO().saveItemsToFile(fileName + "/" + subject + ".xml", entries);

        //response.sendRedirect(url);
    }
}