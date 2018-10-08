package test;

import interfaces.Loadable;
import model.HomeworkEval;
import model.Student;
import model.StudentManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testLoadable {
    Loadable sM;
    ArrayList<Student> classList;

    @Before
    public void setUp(){
        sM = new StudentManager();
        classList = new ArrayList<>();
    }

    @Test
    public void testLoad() throws Exception {
        sM.loadSM(classList);
        Student s0 = classList.get(0);
        List<HomeworkEval> loh0 = s0.getListOfHomework();
        HomeworkEval h0 = loh0.get(0);
        Student s1 = classList.get(1);
        List<HomeworkEval> loh1 = s1.getListOfHomework();
        HomeworkEval h1 = loh1.get(0);
        Double totalS0 = s0.getAttendanceEval().getOutOf();
        Double earnedS0 = s0.getAttendanceEval().getEarned();
        assertEquals("Mary", s0.getfName());
        assertEquals("Cheung", s0.getlName());
        assertEquals("math", h0.getName());
        assertEquals(15.0, h0.getEarned(), 0.0001);
        assertEquals(20.0, h0.getOutOf(), 0.0001);
        assertEquals("Angela", s1.getfName());
        assertEquals("Cheung", s1.getlName());
        assertEquals("math", h1.getName());
        assertEquals(0.0, h1.getEarned(), 0.0001);
        assertEquals(20.0, h1.getOutOf(), 0.0001);
        assertEquals(180.00, totalS0, 0.0001);
        assertEquals(179.00, earnedS0, 0.0001);

    }


}
