package ui;

public class PlayGradeApp {

    public static void main(String[] args) {
        Student student = new Student("Mary",10,20);
        Student student0 = new Student("Angela", 20,20);
        printMark(student);
        printMark(student0);
    }

    private static void printMark(Student student) {
        System.out.println(student.name + "'s mark is " + student.calculateGrade() + "%.");
    }


}
