package test;

import model.Homework;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class testHomework {
    Homework h;

    @Before
    public void setUp() {
        h = new Homework("OSH0", 16);
    }


    @Test
    public void testCalculateGradeNoDecimal(){
        h.setEarned(8);
        assertEquals(8, h.getEarned(), 0.0001);
        h.calculateGrade();
        assertEquals(50.00, h.getGrade(), 0.0001);
    }

    @Test
    public void testCalculateGradeDecimal(){
        h.setEarned(15);
        assertEquals(15, h.getEarned(), 0.0001);
        h.calculateGrade();
        assertEquals(93.75, h.getGrade(), 0.0001);
    }

}
