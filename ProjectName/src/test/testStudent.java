package test;

import model.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class testStudent {
    Student student;

    @Before
    public void setUp(){
        student = new Student("Mary","Cheung");
    }

    @Test
    public void testConstructorNames(){
        assertEquals("Mary", student.getfName());
        assertEquals("Cheung", student.getlName());
    }

    @Test
    public void testGetListOfHomework(){
        assertEquals(new ArrayList<>(), student.getListOfHomework());
    }

}
