package model;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class XmlIO {
    //private ArrayList<LogEntry> entries = new ArrayList<>();


    public void loadItemsFromFile(String filename, ArrayList<LogEntry> entries) {
        try {
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("entry");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
					
					ArrayList<Character> LRs = new ArrayList<>();
					NodeList lrList = eElement.getElementsByTagName("LR");
					for (int i = 0; i < lrList.getLength(); i++)
						LRs.add(lrList.item(i).getTextContent().charAt(0));

					ArrayList<Character> KRs = new ArrayList<>();
					NodeList krList = eElement.getElementsByTagName("KR");
					for (int i = 0; i < krList.getLength(); i++)
						KRs.add(krList.item(i).getTextContent().charAt(0));

					ArrayList<Character> CWs = new ArrayList<>();
					NodeList cwList = eElement.getElementsByTagName("CW");
					for (int i = 0; i < cwList.getLength(); i++)
						CWs.add(cwList.item(i).getTextContent().charAt(0));
		
					ArrayList<Character> exams = new ArrayList<>();
					NodeList examList = eElement.getElementsByTagName("exam");
					for (int i = 0; i < examList.getLength(); i++)
						exams.add(examList.item(i).getTextContent().charAt(0));
	


                    entries.add(new LogEntry(eElement.getElementsByTagName("name").item(0).getTextContent(),
                            LRs, KRs, CWs, exams));

                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ErrorTaskLoading");
        }
        System.out.println(entries);
       
	}

    public void saveItemsToFile(String filename, ArrayList<LogEntry> entries){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("table");
            doc.appendChild(rootElement);

            for (int i = 0; i < entries.size(); i++) {
                Element entry = doc.createElement("entry");
                rootElement.appendChild(entry);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(entries.get(i).getName()));
                entry.appendChild(name);

				ArrayList<Character> LRs = entries.get(i).getLR();
				for(int j = 0; j < LRs.size(); j++){
		            Element LR = doc.createElement("LR");
		            LR.appendChild(doc.createTextNode(LRs.get(j).toString()));
		            entry.appendChild(LR);
				}

				ArrayList<Character> KRs = entries.get(i).getKR();
                for(int j = 0; j < KRs.size(); j++){
		            Element KR = doc.createElement("KR");
		            KR.appendChild(doc.createTextNode(KRs.get(j).toString()));
		            entry.appendChild(KR);
				}

                ArrayList<Character> CWs = entries.get(i).getCW();
                for(int j = 0; j < CWs.size(); j++){
		            Element CW = doc.createElement("CW");
		            CW.appendChild(doc.createTextNode(CWs.get(j).toString()));
		            entry.appendChild(CW);
				}

                ArrayList<Character> exams = entries.get(i).getExam();
                for(int j = 0; j < exams.size(); j++){
		            Element exam = doc.createElement("exam");
		            exam.appendChild(doc.createTextNode(exams.get(j).toString()));
		            entry.appendChild(exam);
				}

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

        } catch (Exception e) {
            throw new RuntimeException("ErrorTaskSaving");
        }
    }
}
