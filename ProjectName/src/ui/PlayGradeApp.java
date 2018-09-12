package ui;

public class PlayGradeApp {

    public static void main(String[] args) {
        Student student = new Student("Mary",10,20);
        System.out.println(student.name + "'s mark is " + student.calculateGrade() + "%");
    }


}
