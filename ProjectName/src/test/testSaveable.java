package test;

import interfaces.Saveable;
import model.AttendanceEval;
import model.HomeworkEval;
import model.Student;
import model.StudentManager;
import org.junit.Before;
import org.junit.Test;
import ui.GUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class testSaveable {
    Saveable sM;
    ArrayList<Student> classList;
    Student s;
    Student s0;

    @Before
    public void setUp() throws IOException {
        sM = new StudentManager();
        classList = new ArrayList<Student>();
        s = new Student("Mary", "Cheung");
        s0 = new Student("Angela", "Cheung");
        classList.add(s);
        classList.add(s0);
        for (Student student:classList) {
            student.addHomeworkEval(new HomeworkEval("math",20, s));
        }
        s.getListOfHomework().get(0).setEarned(15);
        s.setAttendanceEval(new AttendanceEval(180.00, s));
        s0.setAttendanceEval(new AttendanceEval(180.00, s));
        s.getAttendanceEval().setEarned(179.00);
        s0.getAttendanceEval().setEarned(180.00);
        sM.saveSM(classList);


    }

    @Test
    public void testSaveSM() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("outputinput.txt"));
        assertEquals(lines.get(0), "Mary Cheung 180.0 179.0 math 15.0 20.0");
        assertEquals(lines.get(1), "Angela Cheung 180.0 180.0 math 0.0 20.0");
    }
}
