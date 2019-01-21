
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;
import org.junit.*;
import model.*;

public class LogEntryTest{

    @Test
    public void testAddLR() throws Throwable{
        LogEntry underTest = new LogEntry("Test");
        Character lr = '4';
        underTest.addLR(lr);
        Character result = underTest.getLR().get(0);
        assertEquals((Object)'4', (Object)result);
    }


    @Test
    public void testAddKR() throws Throwable{
        LogEntry underTest = new LogEntry("Test");
        Character kr = '3';
        underTest.addKR(kr);
        Character result = underTest.getKR().get(0);
        assertEquals('3', result, 0.0);
    }


    @Test
    public void testAddCW() throws Throwable{
        LogEntry underTest = new LogEntry("Test");
        Character cw = '0';
        underTest.addCW(cw);
        Character result = underTest.getCW().get(0);
        assertEquals('0', result, 0.0);
    }


    @Test
    public void testAddExam() throws Throwable{
        LogEntry underTest = new LogEntry("Test");
        Character exam = '5';
        underTest.addExam(exam);
        Character result = underTest.getExam().get(0);
        assertEquals('5', result, 0.0);
    }


    @Test
    public void testGetTotal() throws Throwable{
        LogEntry underTest = new LogEntry("test");
        underTest.addLR('4');
        underTest.addKR('2');
        underTest.addCW('3');
        underTest.addExam('0');
        int result = underTest.getTotal();
        assertEquals(9, result);
    }


    @Test
    public void testFilter() throws Throwable{
        LogEntry underTest = new LogEntry("test");
        underTest.addLR('4');
        underTest.addKR('2');
        underTest.addCW('3');
        underTest.addExam('0');
        underTest.filter("LR");
        if (underTest.getKR().size() > 0) fail();
        if (underTest.getCW().size() > 0) fail();
        if (underTest.getExam().size() > 0) fail();

    }

}