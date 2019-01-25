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
    PrintWriter out;
    HttpSession session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //entries = new ArrayList<LogEntry>();
        resultEntries = new ArrayList<LogEntry>();

        response.setContentType("text/html");
        out = response.getWriter();
        out.println("<html><body>");
        out.println("<link id=\"tableStyle\" rel=\"stylesheet\" href=\"table.css\"/>");
        session = request.getSession(false);

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
            //searchByGrade(grade, assigment);


            out.println("<div class=\"subject\"> <center><h2>" + subject + "</h2></center>");
            if (resultEntries.size() == 0){
                out.println("</div>");
                continue;
            }

            if (subject.equals("CW") || subject.equals("Exam"))
                showTable(resultEntries, grade);

            else{
                ArrayList<LogEntry> tmp = new ArrayList<>();

                for (int i = 0; i < resultEntries.size(); i++){
                    tmp.add(resultEntries.get(i));
                    showTable(tmp, grade);
                    tmp.remove(0);
                }
            }
            out.println("</div>");
        }
        out.println("<script src=\"javascript/SearchScript.js\"></script>");
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

            if (assigment.equals("LR")) {
                ArrayList<Character> LR = resultEntries.get(i).getLR();

                if (LR.size() == 0 ) {
                    resultEntries.remove(i--);
                    continue;
                }

                for (int j = 0; j < LR.size(); j++){
                    if (!(LR.get(j).equals(grade.charAt(0)))) {
                        LR.remove(j--);
                    }
                }
            }

            else if (assigment.equals("KR")) {
                ArrayList<Character> KR = resultEntries.get(i).getKR();

                if (KR.size() == 0 ) {
                    resultEntries.remove(i--);
                    continue;
                }

                for (int j = 0; j < KR.size(); j++){
                    if (!(KR.get(j).equals(grade.charAt(0)))) {
                        KR.remove(j--);
                    }
                }
            }

            else if (assigment.equals("CW")) {
                ArrayList<Character> CW = resultEntries.get(i).getCW();

                if (CW.size() == 0 ) {
                    resultEntries.remove(i--);
                    continue;
                }

                for (int j = 0; j < CW.size(); j++){
                    if (!(CW.get(j).equals(grade.charAt(0)))) {
                        resultEntries.remove(i--);
                    }
                }
            }

            else{
                ArrayList<Character> exam = resultEntries.get(i).getExam();
                for (int j = 0; j < exam.size(); j++){
                    if (!(exam.get(j).equals(grade.charAt(0)))) {
                        resultEntries.remove(i--);
                    }
                }
            }
        }

    }

    private void showTable(ArrayList<LogEntry> list, String grade){
        out.println("<table>");

        int maxLR = 0;
        for (LogEntry cur : list){
            maxLR = Math.max(maxLR, cur.getLR().size());
        }
        int maxKR = 0;
        for (LogEntry cur : list){
            maxKR = Math.max(maxKR, cur.getKR().size());
        }
        int isCW = 0;
        String isRead = "";
        if (session == null) isRead = "readonly";

        out.print("<tr> <th>Name</th>");
        for(int i = 0; i < maxLR; i++) {
            if (grade.equals(""))
                out.print("<th class=\"LR\">LW" + (i + 1) + "</th>");
            else
            if (list.get(0).getLR().get(i).equals(grade.charAt(0)))
                out.print("<th class=\"LR\">LW" + (i + 1) + "</th>");
        }
        for(int i = 0; i < maxKR; i++) {
            if (grade.equals(""))
                out.print("<th class=\"KR\">Test" + (i + 1) + "</th>");
            else
            if ( list.get(0).getKR().get(i).equals(grade.charAt(0)))
                out.print("<th class=\"KR\">Test" + (i + 1) + "</th>");
        }
        if (list.get(0).getCW().size() == 1) {
            if (grade.equals("")) {
                out.println("<th class=\"CW\">CW</th>");
                isCW = 1;
            }
            else
            if (list.get(0).getCW().get(0).equals(grade.charAt(0))) {
                out.println("<th class=\"CW\">CW</th>");
                isCW = 1;
            }
        }
        if (list.get(0).getExam().size() == 1)
            if (grade.equals(""))
                out.println("<th class=\"Exam\">Exam</th>");
            else
            if (list.get(0).getExam().get(0).equals(grade.charAt(0)))
                out.println("<th class=\"Exam\">Exam</th>");

        out.println("</tr>");

        for(int i = 0; i < list.size(); i++){
            out.println("<tr>");

            out.println("<td> " + list.get(i).getName() + "</td>");

            ArrayList<Character> lrList = list.get(i).getLR();
            for (int j = 0; j < maxLR; j++){
                if (grade.equals("")) {
                    out.print("<td>");

                    if (j < lrList.size())
                        out.println(lrList.get(j) + "</td>");

                    else out.println("0</td>");
                }
                else
                if (lrList.get(j).equals(grade.charAt(0))){
                    out.print("<td>");
                    out.println(lrList.get(j) + "</td>");
                }

            }
            out.print("\n");

            ArrayList<Character> krList = list.get(i).getKR();
            for (int j = 0; j < maxKR; j++){
                if (grade.equals("")) {
                    out.print("<td>");

                    if (j < krList.size())
                        out.println(krList.get(j) + "</td>");

                    else out.println("0</td>");
                }
                else
                if (krList.get(j).equals(grade.charAt(0))){
                    out.print("<td>");
                    out.println(krList.get(j) + "</td>");
                }

            }
            out.print("\n");


            if (list.get(i).getCW().size() == 1) {
                if (grade.equals(""))
                    out.println("<td>" + list.get(i).getCW().get(0) + "</td>");
                else
                if (list.get(i).getCW().get(0).equals(grade.charAt(0)))
                    out.println("<td>" + list.get(i).getCW().get(0) + "</td>");
            }

            if (list.get(i).getExam().size() == 1)
                if (grade.equals(""))
                    out.println("<td>" + list.get(i).getExam().get(0) + "</td>");
                else
                if (list.get(i).getExam().get(0).equals(grade.charAt(0)))
                    out.println("<td>" + list.get(i).getExam().get(0) + "</td>");

            out.println("</tr>");
        }

        out.println("</table><br>");
    }
}