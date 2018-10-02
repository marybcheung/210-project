package interfaces;

import model.Student;

import java.io.IOException;
import java.util.ArrayList;

public interface Saveable {
    //EFFECTS: writes data to testoutputfile.txt
    public void saveSM(ArrayList<Student> classList) throws IOException;
}
