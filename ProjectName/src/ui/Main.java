package ui;

import model.StudentManager;

import java.io.IOException;


//EFFECTS: constructs a new student manager and starts the program
public class Main {
    public static void main(String[] args) throws IOException {
        StudentManager sM = new StudentManager();
        sM.run();
    }
}
