package interfaces;

import model.Student;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    //EFFECTS: pulls data from inputfile.txt and reconstructs classList
    public void loadSM(ArrayList<Student> classList) throws Exception;
}
