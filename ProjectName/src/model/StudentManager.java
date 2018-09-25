package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//used LittleCalculatorStarterLab as a reference
public class StudentManager {
    private ArrayList<Student> classList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    //MODIFIES: this
    //EFFECTS: takes user input to call appropriate function, unless classList is empty, in which case
    //         [2], [3], [4], and [5] do nothing, or if the students have not been assigned any homework,
    //         in which case [3] and [4] do nothing
    public StudentManager(){
        run();
    }

    private void run(){
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("[1] add to class-list");
            System.out.println("[2] assign homework");
            System.out.println("[3] record marks");
            System.out.println("[4] calculate student grades");
            System.out.println("[5] show class-list");
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
            }
        }
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
                System.out.println("Please enter the student's grade");
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
                System.out.println(s.getfName() + " earned " + h.getGrade() + "%" + " for " + h.getName());
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
        for (Student s:classList) {
            s.getListOfHomework().add(new Homework(name,total));
        }
        System.out.println("You have assigned " + name + " to the class.");
        scanner.nextLine();
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
