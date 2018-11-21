package model;

import exceptions.NegativeNumeratorException;
import interfaces.Loadable;
import interfaces.Saveable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import org.json.*;

//used LittleCalculatorStarterLab as a reference
//used FileReaderWriter as a reference
public class StudentManager extends Subject implements Loadable, Saveable, ActionListener{
    static ArrayList<Student> classList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static double totalDays;
    private Printer printer = new Printer();
    private String action;

    //MODIFIES: this
    //EFFECTS: takes user input to call appropriate function, unless classList is empty, in which case
    //         [2], [3], [4], and [5] do nothing, or if the students have not been assigned any homework,
    //         in which case [3] and [4] do nothing
    public void run() throws IOException, JSONException {

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
                case "7": try {loadSM(classList);
                } catch (IOException e){
                    System.out.println("Could not find the save file.");
                }
                break;
                case "8": try {saveSM(classList);
                } catch (IOException e){
                    System.out.println("Could not find the save file.");
                }
                break;
                case "9": break interactionLoop;
            }
        }
    }

    public void run(String action) throws IOException, JSONException {
        interactionLoop: while (true) {
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
                case "7": try {loadSM(classList);
                } catch (IOException e){
                    System.out.println("Could not find the save file.");
                }
                    break;
                case "8": try {saveSM(classList);
                } catch (IOException e){
                    System.out.println("Could not find the save file.");
                }
                    break;
                case "9": break interactionLoop;
            }
        }
    }

    private void checkWeather() throws IOException, JSONException {
        String apikey = "d4d3ad1357a5b82005a201318ed641e5"; //fill this in with the API key they email you
        String weatherquery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
        String theURL = weatherquery+apikey;
        BufferedReader br = null;
        try {
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            System.out.println(sb);
            JSONObject obj = new JSONObject(sb.toString());
            Double temp = obj.getJSONObject("main").getDouble("temp");
            String weather = obj.getJSONArray("weather").getJSONObject(0).getString("description");
            Double tempInCelcius =Math.round ((temp -  273.15) * 10000.0) / 10000.0;
            System.out.println("It is currently " + tempInCelcius + " degrees celcius with " + weather+".");

        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    //REQUIRES: at least one student in the class-List, totalDays is set.
    //MODIFIES: this
    //EFFECTS: marks a student absent, and prints out confirmation
    private void markAbsent() {
        printSelectionDisplay();
        Integer selection = scanner.nextInt() - 1;
        if (selection >= 0 && selection < classList.size()) {
            Student s = classList.get(selection);
            try {
                s.getAttendanceEval().deductMark();
                System.out.println(s.getfName() + " has been marked absent.");
            } catch (NegativeNumeratorException e) {
                System.out.println("Student cannot be absent for more days than there are in the school year.");
            } finally {
                scanner.nextLine();
            }
        }


    }


    //REQUIRES: user input is an integer
    //MODIFIES: this
    //EFFECTS: sets totalDays to user input
    private void setTotalDays() {
        System.out.println("");
        System.out.println("Before adding a student, please enter the total number of days in the school year.");
        totalDays = scanner.nextInt();
        scanner.nextLine();
    }

    //EFFECTS: prints out the list of actions a user can take
    private void printUserChoices() {
        printer.printUserChoices();
    }


    //EFFECTS: writes data to outputinput.txt
    @Override
    public void saveSM(ArrayList<Student> classList0) throws IOException {
        List<String> lines = new ArrayList<String>();
        PrintWriter writer = new PrintWriter("outputinput.txt","UTF-8");
        for (Student s: classList0) {
            addStudentInfoToLines(lines, s);
        }
        for (String line: lines) {
            writer.println(line);
        }
        writer.close();
        System.out.println("You have saved the class-list");
    }

    //This was originally in the saveSM method, extracted for better readability
    private void addStudentInfoToLines(List<String> lines, Student s) {
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


    //EFFECTS: pulls data from inputfile.txt and reconstructs classList
    @Override
    public void loadSM(ArrayList<Student> classList) throws IOException {
        List<String> inputLines = Files.readAllLines(Paths.get("outputinput.txt"));
        if (inputLines.size() != 0) {
            for (String line : inputLines) {
                ArrayList<String> inputsList = splitOnSpace(line);
                Student s = loadStudent(inputsList);
                if (inputsList.size() > 4) {
                    loadHomework(inputsList, s);
                }
                classList.add(s);
                addObserver(s);
            }
            System.out.println("You have loaded a class-list.");
        } else {
            System.out.println("There is no saved class-list.");
        }
    }

    //loadStudent and loadHomework were originally in loadSM but taken out for better readability
    private Student loadStudent(ArrayList<String> inputsList) {
        Student s = new Student(inputsList.get(0), inputsList.get(1));
        AttendanceEval attendance = new AttendanceEval(Double.parseDouble(inputsList.get(2)), s);
        totalDays = Double.parseDouble(inputsList.get(2));
        attendance.setEarned(Double.parseDouble(inputsList.get(3)));
        s.setAttendanceEval(attendance);
        return s;
    }

    private void loadHomework(ArrayList<String> inputsList, Student s) {
        for (int i = 4; i < inputsList.size(); i += 3) {
            HomeworkEval h = new HomeworkEval(inputsList.get(i), (int) Math.round(Double.parseDouble(inputsList.get(i + 2))), s);
            h.setEarned((int) Math.round(Double.parseDouble(inputsList.get(i + 1))));
            s.getListOfHomework().add(h);
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
            List<HomeworkEval> loh = s.getListOfHomework();
            printHomeworkSelection(loh);
            Integer hSelection = scanner.nextInt() - 1;
            recordTheMark(s, loh, hSelection);
        }
        scanner.nextLine();
    }

    private void recordTheMark(Student s, List<HomeworkEval> loh, Integer selection) {
        if (selection >= 0 && selection < loh.size()) {
            HomeworkEval homework = loh.get(selection);
            System.out.println("Total marks: " + homework.getOutOf() + ". " + "Please enter the student's grade");
            Integer grade = scanner.nextInt();
            try {
                homework.setEarned(grade);
                System.out.println("You have recorded " + s.getfName() + "'s mark.");
            } catch (NegativeNumeratorException e) {
                System.out.println("Cannot set mark to a negative integer.");
            }
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
            HomeworkEval h = new HomeworkEval(name, total, s);
            s.getListOfHomework().add(h);
        }
        notifyObservers(name, total);
        System.out.println("You have assigned " + name + " to the class.");
        scanner.nextLine();
    }


    //EFFECTS: prints out a student's first and last name, as well as certain needs for all students in the classList
    private void printList(List<Student> classList) {
        printer.printList(classList);
    }

    //note: printHomeworkSelection and printSelectionDisplay were refactored out to reduce duplication
    //REQUIRES: loh is not an empty list
    //EFFECTS: prints out a numbered selection of all homework
    private void printHomeworkSelection(List<HomeworkEval> loh) {
        printer.printHomeworkSelection(loh);
    }

    //EFFECTS: prints out all students in the classList, numbered starting from 1,
    private void printSelectionDisplay() {
        printer.printSelectionDisplay(classList);
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
        s.setAttendanceEval(new AttendanceEval(totalDays, s));
        classList.add(s);
        addObserver(s);
        System.out.println("You have added " + fName + " " + lName + " " + "to the class-list.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scanner.nextLine();
    }
}
