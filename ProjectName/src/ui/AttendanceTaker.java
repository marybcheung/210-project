package ui;

import java.util.ArrayList;
import java.util.Scanner;

//used LittleCalculatorStaterLab as a reference
public class AttendanceTaker {
    ArrayList<Student> classList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public AttendanceTaker(){
        while (true) {
            System.out.println("What would you like to do? [1] add to class-list, [2] mark student absent, [3] show list.");
            String action = scanner.nextLine();

            if (action.equals("1")) {
                createStudent();
            } else if (action.equals("2")){
                markAbsent();
            } else if (action.equals("3")){
                printList(classList);
            }
        }
    }

    private void printList(ArrayList<Student> classList) {
        for (Student s: classList
             ) {
            System.out.println(s.fName + " " + s.lName + " is " + s.attendance + ".");
        }
    }

    private void markAbsent() {
        System.out.println("Please select a student");
        int i = 1;
        for (Student s : classList) {
            System.out.println("[" + i + "]" + " " + s.fName + " " + s.lName);
            i++;
        }
        Integer selection = scanner.nextInt() - 1;
        Student student = classList.get(selection);
        student.setAttendance(false);
        System.out.println(student.fName + " " + student.lName + " has been marked " + student.getAttendance() + ".");
        scanner.nextLine();
    }


    private void createStudent() {
        System.out.println("Please enter the student's first name.");
        String fName = scanner.nextLine();
        System.out.println("Please enter the student's last name.");
        String lName = scanner.nextLine();
        classList.add(new Student(fName, lName));
        System.out.println("You have added " + fName + " " + lName + " " + "to the class-list.");
    }

    public static void main(String[] args) { new AttendanceTaker();}
}
