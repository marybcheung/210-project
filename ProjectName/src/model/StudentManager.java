package model;

import interfaces.Loadable;
import interfaces.Saveable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//used LittleCalculatorStarterLab as a reference
//used FileReaderWriter as a reference
public class StudentManager implements Loadable, Saveable{
    static ArrayList<Student> classList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int totalDays;


    //MODIFIES: this
    //EFFECTS: takes user input to call appropriate function, unless classList is empty, in which case
    //         [2], [3], [4], and [5] do nothing, or if the students have not been assigned any homework,
    //         in which case [3] and [4] do nothing
    public void run() throws IOException {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("[1] add to class-list");
            System.out.println("[2] assign homework");
            System.out.println("[3] record marks");
            System.out.println("[4] calculate student grades");
            System.out.println("[5] show class-list");
            System.out.println("[6] load saved class-list");
            System.out.println("[7] save current class-list");
            System.out.println("[8] quit the application");
            String action = scanner.nextLine();

            if (action.equals("1")) {
                createStudent();
            } else if (action.equals("2")){
                if (classList.size() != 0){
                    assignClassHomework();
                }
            } else if (action.equals("3")){
                if (classList.size() != 0 && classList.get(0).getListOfHomework().size() != 0) {
                    recordMarks();
                }
            } else if (action.equals("4")){
                if(classList.size() != 0){
                    calculateStudentGrades();
                }
            } else if (action.equals("5")){
                if(classList.size() != 0){
                    printList(classList);
                }
            } else if (action.equals("6")){
                loadSM(classList);
            } else if (action.equals("7")){
                saveSM(classList);
            } else if (action.equals("8")){
                break;
            }
        }
    }


    //EFFECTS: writes data to testoutputfile.txt
    @Override
    public void saveSM(ArrayList<Student> classList0) throws IOException {
        List<String> lines = new ArrayList<String>(); //if you want to include all the lines in inputfile.txt,
        // use  = Files.readAllLines(Paths.get("inputfile.txt"))
        PrintWriter writer = new PrintWriter("outputinput.txt","UTF-8");
        for (Student s: classList0) {
            List<Homework> loh = s.getListOfHomework();
            if (loh.size() == 0){
                lines.add(s.getfName()+ " " + s.getlName());
            } else{
                String finalString = s.getfName()+ " " + s.getlName();
                for (Homework h: loh) {
                     finalString += " "+ h.getName()+ " " + h.getEarned() + " " + h.getOutOf();
                }
                lines.add(finalString);
            }

        }
        for (String line: lines) {
            writer.println(line);
        }
        writer.close();
        System.out.println("You have saved the class-list");
    }


    //EFFECTS: pulls data from inputfile.txt and reconstructs classList
    @Override
    public void loadSM(ArrayList<Student> classList) throws IOException{
//        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
//        PrintWriter writer = new PrintWriter("inputfile.txt","UTF-8");
//        for (String line:lines) {
//            writer.println(line);
//        }
//        writer.close();
        List<String> inputLines = Files.readAllLines(Paths.get("outputinput.txt"));
        for (String line:inputLines) {
            ArrayList<String> inputsList = splitOnSpace(line);
            Student s = new Student(inputsList.get(0), inputsList.get(1));
            if (inputsList.size() > 2){
                for (int i = 2; i <inputsList.size();i+=3){
                    Homework h = new Homework(inputsList.get(i), (int) Math.round(Double.parseDouble(inputsList.get(i+2))));
                    h.setEarned((int)Math.round(Double.parseDouble(inputsList.get(i+1))));
                    s.getListOfHomework().add(h);
                }
            }
            classList.add(s);
        }
        System.out.println("You have loaded a class-list.");
    }

    //EFFECTS: splits up a line at every space and returns the list of strings
    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: grade >= 0 && grade <= hl.getOutOf()
    //MODIFIES: this
    //EFFECTS: sets selected Student's mark in selected Homework
    private void recordMarks() {
        printSelectionDisplay();
        Integer selection = scanner.nextInt() - 1;
        if (selection >= 0 && selection < classList.size()) {
            Student s = classList.get(selection);
            System.out.println("Please select an assignment.");
            Integer i = 1;
            List<Homework> hl = s.getListOfHomework();
            for (Homework h : hl) {
                System.out.println("[" + i + "] " + h.getName());
                i++;
            }
            Integer hSelection = scanner.nextInt() - 1;
            if (hSelection >= 0 && hSelection < hl.size()) {
                Homework homework = hl.get(hSelection);
                System.out.println("Total marks: " + homework.getOutOf() + ". " + "Please enter the student's grade");
                Integer grade = scanner.nextInt();
                homework.setEarned(grade);
                System.out.println("You have recorded " + s.getfName() + "'s mark.");
            }
        }
        scanner.nextLine();
    }

    //MODIFIES: this
    //EFFECTS: calculates % grade for each Homework in selected Student's
    //         listOfHomework and prints out said grade.
    private void calculateStudentGrades() {
        printSelectionDisplay();
        Integer selection = scanner.nextInt() - 1;
        if (selection >= 0 && selection < classList.size()) {
            Student s = classList.get(selection);
            List<Homework> hList = s.getListOfHomework();
            for (Homework h : hList) {
                h.calculateGrade();
                System.out.println(s.getfName() + " earned " + h.calculateGrade() + "%" + " for " + h.getName());
            }
        }
        scanner.nextLine();
    }


    //MODIFIES: this
    //EFFECTS: constructs a new Homework and adds it to listOfHomework for every Student
    //         in the class-list
    private void assignClassHomework() {
        System.out.println("Please enter a name for this assignment.");
        String name = scanner.nextLine();
        System.out.println("Please enter the total marks.");
        Integer total = scanner.nextInt();
        aCHHelper(name, total);
        System.out.println("You have assigned " + name + " to the class.");
        scanner.nextLine();
    }

    private void aCHHelper(String name, Integer total) {
        for (Student s:classList) {
            s.getListOfHomework().add(new Homework(name,total));
        }
    }


    //EFFECTS: prints out a student's first and last name for all students in the classList
    private void printList(ArrayList<Student> classList) {
        for (Student s: classList
             ) {
            System.out.println(s.getfName() + " " + s.getlName());
        }
    }



    //EFFECTS: prints out all students in the classList, numbered starting from 1,
    private void printSelectionDisplay() {
            System.out.println("Please select a student");
            int i = 1;
            for (Student s : classList) {
                System.out.println("[" + i + "]" + " " + s.getfName() + " " + s.getlName());
                i++;
            }
        }

    //MODIFIES: this
    //EFFECTS: creates a new Student, adds this Student to the classList,
    //         and prints a line confirming this action
    private void createStudent() {
        System.out.println("Please enter the student's first name.");
        String fName = scanner.nextLine();
        System.out.println("Please enter the student's last name.");
        String lName = scanner.nextLine();
        classList.add(new Student(fName, lName));
        System.out.println("You have added " + fName + " " + lName + " " + "to the class-list.");
    }

}
