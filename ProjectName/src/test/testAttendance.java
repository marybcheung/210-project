package test;

import exceptions.NegativeNumeratorException;
import model.AttendanceEval;
import model.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testAttendance {

    AttendanceEval a;

    @Before
    public void setUp() {
        a = new AttendanceEval(180, new Student("Mary", "Cheung"));
    }

    @Test
    public void testCalculateGradeAttendanceNoDecimal() throws NegativeNumeratorException {
        assertEquals(100.00, a.calculateGrade(), 0.0001);
        for (int i = 0; i < 90; i++){
            a.deductMark();
        }
        assertEquals(50.00, a.calculateGrade(), 0.0001);

    }

    @Test
    public void testCalculateGradeAttendanceDecimal() throws NegativeNumeratorException {
        a.deductMark();
        assertEquals(99.44, a.calculateGrade(), 0.0001);
    }
}
