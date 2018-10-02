package test.ForLater;

import model.ForLater.Attendance;
import model.Homework;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Assessment {
    Homework h;
    Attendance a;

    @Before
    public void setUp() {
        h = new Homework("OSH0", 16);
        a = new Attendance(180);
    }
    @Test
    public void testCalculateGradeHomeworkNoDecimal(){
        h.setEarned(8);
        assertEquals(8, h.getEarned(), 0.0001);
        h.calculateGrade();
        assertEquals(50.00, h.calculateGrade(), 0.0001);
    }

    @Test
    public void testCalculateGradeHomeworkDecimal(){
        h.setEarned(15);
        assertEquals(15, h.getEarned(), 0.0001);
        h.calculateGrade();
        assertEquals(93.75, h.calculateGrade(), 0.0001);
    }

    @Test
    public void testCalculateGradeAttendanceNoDecimal(){
        assertEquals(100.00, a.calculateGrade(), 0.0001);
        for (int i = 0; i < 90; i++){
            a.deductMark();
        }
        assertEquals(50.00, a.calculateGrade(), 0.0001);

    }

    @Test
    public void testCalculateGradeAttendanceDecimal(){
        a.deductMark();
        assertEquals(99.44, a.calculateGrade(), 0.0001);
    }
}
