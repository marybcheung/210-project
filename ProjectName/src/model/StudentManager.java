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
    static double totalDays;


    //MODIFIES: this
    //EFFECTS: takes user input to call appropriate function, unless classList is empty, in which case
    //         [2], [3], [4], and [5] do nothing, or if the students have not been assigned any homework,
    //         in which case [3] and [4] do nothing
    public void run() throws IOException {
        interactionLoop: while (true) {
            printUserChoices();
            String action = scanner.nextLine();
            switch (action){
                case "1": if(totalDays == 0){ setTotalDays();}
                createStudent();
                break;
                case "2": if (classList.size() != 0){assignClassHomework();}
                break;
                case "3": if (classList.size() != 0 && classList.get(0).getListOfHomework().size() != 0) {recordMarks(); }
                break;
                case "4": if(classList.size() != 0){calculateStudentGrades();}
                break;
                case "5": if(classList.size() != 0) {printList(classList); }
                break;
                case "6": if(classList.size() != 0) {markAbsent();}
                break;
                case "7": loadSM(classList);
                break;
                case "8": saveSM(classList);
                break;
                case "9" : break interactionLoop;
            }
        }
    }

    //REQUIRES: at least one student in the class-List, totalDays is set
    //MODIFIES: this
    //EFFECTS: marks a student absent, and prints out confirmation
    private void markAbsent() {
        printSelectionDisplay();
        Integer selection = scanner.nextInt() - 1;
        if (selection >= 0 && selection < classList.size()) {
            Student s = classList.get(selection);
            s.getAttendanceEval().deductMark();
            System.out.println(s.getfName() + " has been marked absent.");
        }
        scanner.nextLine();

    }


    //REQUIRES: user input is an integer
    //MODIFIES: this
    //EFFECTS: sets totalDays to user input
    private void setTotalDays() {
        System.out.println("Before adding a student, please enter the total number of days in the school year.");
        totalDays = scanner.nextInt();
        scanner.nextLine();
    }

    //EFFECTS: prints out the list of actions a user can take
    private void printUserChoices() {
        System.out.println("What would you like to do?");
        System.out.println("[1] add to class-list");
        System.out.println("[2] assign homework");
        System.out.println("[3] record marks");
        System.out.println("[4] calculate student grades");
        System.out.println("[5] show class-list alerts");
        System.out.println("[6] mark a student absent");
        System.out.println("[7] load saved class-list");
        System.out.println("[8] save current class-list");
        System.out.println("[9] quit the application");
    }


    //EFFECTS: writes data to outputinput.txt
    @Override
    public void saveSM(ArrayList<Student> classList0) throws IOException {
        List<String> lines = new ArrayList<String>();
        PrintWriter writer = new PrintWriter("outputinput.txt","UTF-8");
        for (Student s: classList0) {
            List<HomeworkEval> loh = s.getListOfHomework();
            AttendanceEval attendanceEval = s.getAttendanceEval();
            String finalString = s.getfName() + " " + s.getlName() + " " + attendanceEval.getOutOf() + " " + attendanceEval.getEarned();
            if(loh.size() != 0){
                for (HomeworkEval h: loh) {
                     finalString += " "+ h.getName()+ " " + h.getEarned() + " " + h.getOutOf();
                }
            }
            lines.add(finalString);

        }
        for (String line: lines) {
            writer.println(line);
        }
        writer.close();
        System.out.println("You have saved the class-list");
    }


    //EFFECTS: pulls data from inputfile.txt and reconstructs classList
    @Override
    public void loadSM(ArrayList<Student> classList) throws IOException {
        List<String> inputLines = Files.readAllLines(Paths.get("outputinput.txt"));
        if (inputLines.size() != 0) {
            for (String line : inputLines) {
                ArrayList<String> inputsList = splitOnSpace(line);
                Student s = new Student(inputsList.get(0), inputsList.get(1));
                AttendanceEval attendance = new AttendanceEval(Double.parseDouble(inputsList.get(2)));
                attendance.setEarned(Double.parseDouble(inputsList.get(3)));
                s.setAttendanceEval(attendance);
                if (inputsList.size() > 4) {
                    for (int i = 4; i < inputsList.size(); i += 3) {
                        HomeworkEval h = new HomeworkEval(inputsList.get(i), (int) Math.round(Double.parseDouble(inputsList.get(i + 2))));
                        h.setEarned((int) Math.round(Double.parseDouble(inputsList.get(i + 1))));
                        s.getListOfHomework().add(h);
                    }
                }
                classList.add(s);
            }
            System.out.println("You have loaded a class-list.");
        } else {
            System.out.println("There is no saved class-list.");
        }
    }

    //EFFECTS: splits up a line at every space and returns the list of strings
    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: grade >= 0 && grade <= hl.getOutOf()
    //MODIFIES: this
    //EFFECTS: sets selected Student's mark in selected HomeworkEval
    private void recordMarks() {
        printSelectionDisplay();
        Integer selection = scanner.nextInt() - 1;
        if (selection >= 0 && selection < classList.size()) {
            Student s = classList.get(selection);
            System.out.println("Please select an assignment.");
            List<HomeworkEval> loh = s.getListOfHomework();
            printHomeworkSelection(loh);
            Integer hSelection = scanner.nextInt() - 1;
            recordMarksHelper(s, loh, hSelection);
        }
        scanner.nextLine();
    }

    private void recordMarksHelper(Student s, List<HomeworkEval> loh, Integer selection) {
        if (selection >= 0 && selection < loh.size()) {
            HomeworkEval homework = loh.get(selection);
            System.out.println("Total marks: " + homework.getOutOf() + ". " + "Please enter the student's grade");
            Integer grade = scanner.nextInt();
            homework.setEarned(grade);
            System.out.println("You have recorded " + s.getfName() + "'s mark.");
        }
    }

    //REQUIRES: loh is not an empty list
    //EFFECTS: prints out a numbered selection of all homework
    private void printHomeworkSelection(List<HomeworkEval> loh) {
        Integer i = 1;
        for (HomeworkEval h : loh) {
            System.out.println("[" + i + "] " + h.getName());
            i++;
        }
    }

    //MODIFIES: this
    //EFFECTS: calculates % grade for each HomeworkEval in selected Student's
    //         listOfHomework and prints out said grade.
    private void calculateStudentGrades() {
        printSelectionDisplay();
        Integer selection = scanner.nextInt() - 1;
        if (selection >= 0 && selection < classList.size()) {
            Student s = classList.get(selection);
            List<HomeworkEval> hList = s.getListOfHomework();
            for (HomeworkEval h : hList) {
                h.calculateGrade();
                System.out.println(s.getfName() + " earned " + h.calculateGrade() + "%" + " for " + h.getName());
            }
        }
        scanner.nextLine();
    }


    //MODIFIES: this
    //EFFECTS: constructs a new HomeworkEval and adds it to listOfHomework for every Student
    //         in the class-list
    private void assignClassHomework() {
        System.out.println("Please enter a name for this assignment.");
        String name = scanner.nextLine();
        System.out.println("Please enter the total marks.");
        Integer total = scanner.nextInt();
        for (Student s:classList) {
            s.getListOfHomework().add(new HomeworkEval(name,total));
        }
        System.out.println("You have assigned " + name + " to the class.");
        scanner.nextLine();
    }


    //EFFECTS: prints out a student's first and last name, as well as certain needs for all students in the classList
    private void printList(ArrayList<Student> classList) {
        for (Student s: classList){
            String finalString = s.getfName() + " " + s.getlName();
            for (HomeworkEval h: s.getListOfHomework()) {
                finalString += h.studentNeeds();
            }
            System.out.println(finalString + s.getAttendanceEval().studentNeeds());
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
        Student s = new Student(fName, lName);
        s.setAttendanceEval(new AttendanceEval(totalDays));
        classList.add(s);
        System.out.println("You have added " + fName + " " + lName + " " + "to the class-list.");
    }

}
