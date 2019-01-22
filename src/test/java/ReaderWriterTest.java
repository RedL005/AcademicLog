
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;
import java.util.*;
import org.junit.*;
import java.io.*;
import model.*;

public class ReaderWriterTest{

    @Test
    public void testRead() throws Throwable{
        ReaderWriter underTest = new ReaderWriter();
        TreeMap<String, String> expected = new TreeMap<>();
        TreeMap<String, String> result = new TreeMap<>();
        expected.put("test1", "password1");
        expected.put("test2", "password2");
        String fileName = new String("./src/test/resources/readTest.txt");
        underTest.read(fileName, result);
        assertEquals(expected, result);
    }

    @Test
    public void testReadSubjects() throws Throwable{
        ReaderWriter underTest = new ReaderWriter();
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("test");
        expected.add("another Test");
        String fileName = new String("./src/test/resources/readSubjectsTest.txt");
        underTest.readSubjects(fileName, result);
        assertEquals(expected, result);
    }

    @Test
    public void testWrite() throws Throwable{
        ReaderWriter underTest = new ReaderWriter();
        TreeMap<String, String> expected = new TreeMap<>();
        expected.put("test1", "password1");
        expected.put("test98", "pass24");
        String fileName = new String("./src/test/resources/writeTest.txt");
        underTest.write(fileName, expected);
        TreeMap<String, String> result = new TreeMap<>();
        underTest.read(fileName, result);
        assertEquals(expected, result);
    }

    @Test
    public void testWriteSubjects() throws Throwable{
        ReaderWriter underTest = new ReaderWriter();
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("test");
        expected.add("another Test");
        String fileName = new String("./src/test/resources/writeSubjectsTest.txt");
        underTest.writeSubjects(fileName, expected);
        underTest.readSubjects(fileName, result);
        assertEquals(expected, result);
    }

}