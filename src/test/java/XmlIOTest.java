

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;
import java.util.*;
import org.junit.*;
import java.io.*;
import model.*;

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

public class XmlIOTest{


    @Test
    public void testLoadItemsFromFile() throws Throwable{
        XmlIO underTest = new XmlIO();
        ArrayList<LogEntry> expected = new ArrayList<>();
        ArrayList<LogEntry> result = new ArrayList<>();
        expected.add(new LogEntry("test1"));
        expected.add(new LogEntry("test2"));
        String fileName = new String("./src/test/resources/loadItemsTest.xml");
        underTest.saveItemsToFile(fileName, expected);
        underTest.loadItemsFromFile(fileName, result);
        assertEquals(expected, result);
    }


    @Test
    public void testSaveItemsToFile() throws Throwable{
        XmlIO underTest = new XmlIO();
        ArrayList<LogEntry> expected = new ArrayList<>();
        ArrayList<LogEntry> result = new ArrayList<>();
        expected.add(new LogEntry("test1"));
        expected.add(new LogEntry("test2"));
        String fileName = new String("./src/test/resources/saveItemsTest.xml");
        underTest.saveItemsToFile(fileName, expected);
        underTest.loadItemsFromFile(fileName, result);
        assertEquals(expected, result);
    }
}