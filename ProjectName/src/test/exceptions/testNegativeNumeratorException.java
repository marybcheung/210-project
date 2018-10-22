package test.exceptions;

import exceptions.NegativeNumeratorException;
import model.AttendanceEval;
import model.HomeworkEval;
import model.Student;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class testNegativeNumeratorException {
    Student testStudent;
    HomeworkEval h0;

    @Before
    public void setUp(){
        testStudent = new Student("Mary","Cheung");
        testStudent.setAttendanceEval(new AttendanceEval(1));
        h0 = new HomeworkEval("math", 20);
    }

    @Test
    public void testSetEarnedHomeworkPositive(){
        try {
            h0.setEarned(15);
        } catch (NegativeNumeratorException e) {
            fail("I was not expecting an exception.");
        }
    }

    @Test
    public void testSetEarnedHomeworkNegative(){
        try{
            h0.setEarned(-15);
            fail("I was expecting an exception.");
        } catch (NegativeNumeratorException e){
            System.out.println("Great!");
        }
    }

    @Test
    public void testDeductAttendanceOnceAndTwice(){
        try{
            testStudent.getAttendanceEval().deductMark();
        } catch (NegativeNumeratorException e){
            fail("I was not expecting an expection");
        }

        try{
            testStudent.getAttendanceEval().deductMark();
            fail();
        } catch (NegativeNumeratorException e){
            System.out.println("Great!");
        }
    }
}
