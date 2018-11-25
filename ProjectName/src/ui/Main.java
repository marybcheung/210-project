package ui;

import model.StudentManager;
import org.json.JSONException;

import java.io.IOException;


//EFFECTS: constructs a new student manager and starts the program
public class Main {
    public static void main(String[] args) throws IOException, JSONException, InterruptedException {
        StudentManager sM = new StudentManager();
        sM.run();
    }
}
