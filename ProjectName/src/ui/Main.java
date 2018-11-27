package ui;

import model.StudentManager;
import org.json.JSONException;

import java.io.IOException;


//EFFECTS: constructs a new student manager and starts the program, HOWEVER
//         this method was used when the program used console input. Please use
//         the main method in the GUI class instead.
public class Main {
    public static void main(String[] args) throws IOException, JSONException, InterruptedException {
        StudentManager sM = new StudentManager();
        sM.run();
    }
}
