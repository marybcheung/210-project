package test;

import interfaces.Saveable;
import model.Homework;
import model.Student;
import model.StudentManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
            student.getListOfHomework().add(new Homework("math",20));
        }
        s.getListOfHomework().get(0).setEarned(15);
        sM.saveSM(classList);


    }

    @Test
    public void testSaveSM() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("outputinput.txt"));
        assertEquals(lines.get(0), "Mary Cheung math 15.0 20.0");
        assertEquals(lines.get(1), "Angela Cheung math 0.0 20.0");
    }
}
