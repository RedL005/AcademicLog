
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;
import org.junit.*;
import model.*;

public class LogEntryTest{

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