package model;

import java.util.List;

public class Printer {

    public void printUserChoices() {
        System.out.println("");
        System.out.println("What would you like to do?");
        System.out.println("[1] add to class-list");
        System.out.println("[2] assign homework");
        System.out.println("[3] record marks");
        System.out.println("[4] calculate student grades");
        System.out.println("[5] show class-list alerts");
        System.out.println("[6] mark a student absent");
        System.out.println("[7] load saved class-list");
        System.out.println("[8] save current class-list");
//        System.out.println("[9] check the weather");
        System.out.println("[9] quit the application");
    }

    public void printList(List<Student> classList) {
        for (Student s: classList){
            String finalString = s.getfName() + " " + s.getlName();
            for (HomeworkEval h: s.getListOfHomework()) {
                finalString += h.studentNeeds();
            }
            System.out.println(finalString + s.getAttendanceEval().studentNeeds());
        }
    }

    public void printHomeworkSelection(List<HomeworkEval> loh) {
        System.out.println("Please select an assignment.");
        int i = 1;
        for (HomeworkEval h : loh) {
            System.out.println("[" + i + "] " + h.getName());
            i++;
        }
    }

    public void printSelectionDisplay(List<Student> classList) {
        System.out.println("Please select a student");
        int i = 1;
        for (Student s : classList) {
            System.out.println("[" + i + "]" + " " + s.getfName() + " " + s.getlName());
            i++;
        }
    }
}
