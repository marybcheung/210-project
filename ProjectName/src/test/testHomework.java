package test;

import model.HomeworkEval;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class testHomework {
    HomeworkEval h;

    @Before
    public void setUp() {
        h = new HomeworkEval("OSH0", 16);
    }

    @Test
    public void testConstructor(){
        assertEquals("OSH0",h.getName());
        assertEquals(16, h.getOutOf(), 0.0001);
    }


    @Test
    public void testCalculateGradeNoDecimal(){
        h.setEarned(8);
        assertEquals(8, h.getEarned(), 0.0001);
        h.calculateGrade();
        assertEquals(50.00, h.calculateGrade(), 0.0001);
    }

    @Test
    public void testCalculateGradeDecimal(){
        h.setEarned(15);
        assertEquals(15, h.getEarned(), 0.0001);
        h.calculateGrade();
        assertEquals(93.75, h.calculateGrade(), 0.0001);
        }
}
