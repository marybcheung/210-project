package test.ForLater;

import model.ForLater.Attendance;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkDeductible {
    Attendance a;

    @Before
    public void setUp(){
        a = new Attendance(180);
    }

    @Test
    public void testDeductMarkOnce(){
        a.deductMark();
        assertEquals(179, a.getAttendedDays(), 0.0001);
    }

    @Test
    public void testDeductMarkMultipleTimes(){
        a.deductMark();
        a.deductMark();
        a.deductMark();
        a.deductMark();
        assertEquals(176, a.getAttendedDays(), 0.0001);
    }
}
