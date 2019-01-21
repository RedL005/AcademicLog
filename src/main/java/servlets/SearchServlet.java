package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.TreeMap;
import model.*;

public class SearchServlet extends HttpServlet {
    ArrayList<LogEntry> entries;
    ArrayList<LogEntry> resultEntries;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //entries = new ArrayList<LogEntry>();
        resultEntries = new ArrayList<LogEntry>();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<link id=\"tableStyle\" rel=\"stylesheet\" href=\"table.css\"/>");
        HttpSession session = request.getSession(false);

        String name = request.getParameter("searchName");
        String assigment = request.getParameter("Assigment");
        String grade = request.getParameter("Grade");


        String logs = new String("/home/osboxes/IdeaProjects/AcademicLog/src/main/webapp/subjects.txt");
        ArrayList<String> subjects = new ArrayList<>();
        new ReaderWriter().readSubjects(logs, subjects);
        //new XmlIO().loadItemsFromFile(fileName, entries);

        for (int k = 0; k < subjects.size(); k++){

            entries = new ArrayList<LogEntry>();
            resultEntries = new ArrayList<LogEntry>();
            String subject = subjects.get(k);
            String fileName = new String("/home/osboxes/IdeaProjects/AcademicLog/src/main/webapp/" + subject + ".xml");
            new XmlIO().loadItemsFromFile(fileName, entries);

            searchByName(name);
            searchByAssigment(assigment);
            searchByGrade(grade, assigment);


            out.println("<center><h2>" + subject + "</h2></center>");
            if (resultEntries.size() == 0){
                out.println("<center>No entries that much search parameters</center><br><br><br>");
                continue;
            }
            out.println("<table>");

            int maxLR = 0;
            for (LogEntry cur : resultEntries){
                maxLR = Math.max(maxLR, cur.getLR().size());
            }
            int maxKR = 0;
            for (LogEntry cur : resultEntries){
                maxKR = Math.max(maxKR, cur.getKR().size());
            }
            int isCW = 0;
            String isRead = "";
            if (session == null) isRead = "readonly";

            out.print("<tr> <th>Name</th>");
            for(int i = 0; i < maxLR; i++)
                out.print("<th>LW" + (i + 1) + "</th>");
            for(int i = 0; i < maxKR; i++)
                out.print("<th>Test" + (i + 1) + "</th>");
            if (resultEntries.get(0).getCW().size() == 1) {
                out.println("<th>CW</th>");
                isCW = 1;
            }
            if (resultEntries.get(0).getExam().size() == 1)
                out.println("<th>Exam</th>");

            out.println("</tr>");

            for(int i = 0; i < resultEntries.size(); i++){
                out.println("<tr>");

                out.println("<td> " + resultEntries.get(i).getName() + "</td>");

                ArrayList<Character> lrList = resultEntries.get(i).getLR();
                for (int j = 0; j < maxLR; j++){
                    out.print("<td>");

                    if ( j < lrList.size())
                        out.println(lrList.get(j) + "</td>");

                    else out.println("0</td>");

                }
                out.print("\n");

                ArrayList<Character> krList = resultEntries.get(i).getKR();
                for (int j = 0; j < maxKR; j++){
                    out.print("<td>");

                    if ( j < krList.size())
                        out.println(krList.get(j) + "</td>");

                    else out.println("0</td>");

                }
                out.print("\n");


                if (resultEntries.get(i).getCW().size() == 1) {
                    out.println("<td>" + resultEntries.get(i).getCW().get(0) + "</td>");
                }

                if (resultEntries.get(i).getExam().size() == 1)
                    out.println("<td>" + resultEntries.get(i).getExam().get(0) + "</td>");

                out.println("</tr>");
            }

            out.println("</table><br><br><br>");




        }

        out.println("</body></html>");
    }


    private void searchByName(String name){

        if (name.equals("")){
            for (int i = 0; i < entries.size(); i++){
                resultEntries.add(entries.get(i));
            }
        }

        else{
            for (int i = 0; i < entries.size(); i++){
                if (entries.get(i).getName().equals(name))
                    resultEntries.add(entries.get(i));
            }
        }

    }

    private void searchByAssigment(String assigment){

        for (int i = 0; i < resultEntries.size(); i++)
            resultEntries.get(i).filter(assigment);

    }

    private void searchByGrade(String grade, String assigment){

        if (grade == null) return;
        for (int i = 0; i < resultEntries.size(); i++) {

            if (assigment.equals("CW")) {
                ArrayList<Character> CW = resultEntries.get(i).getCW();

                if (CW.size() == 0 ) {
                    resultEntries.remove(i--);
                    continue;
                }

                for (int j = 0; j < CW.size(); j++){
                    if (!(CW.get(j).equals(grade.charAt(0)))) {
                        resultEntries.remove(i--);
                        continue;
                    }
                }
            }

            else{
                ArrayList<Character> exam = resultEntries.get(i).getExam();
                for (int j = 0; j < exam.size(); j++){
                    if (!(exam.get(j).equals(grade.charAt(0)))) {
                        resultEntries.remove(i--);
                        continue;
                    }
                }
            }
        }

    }
}