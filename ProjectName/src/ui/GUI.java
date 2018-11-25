package ui;



import jdk.internal.util.xml.impl.Input;
import model.StudentManager;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI extends JFrame implements ActionListener{

    private String action;
    private List<JButton> labels = new ArrayList<>();
    private JTextField field;
    private StudentManager studentManager;

    public GUI(StudentManager studentManager)
    {
        super("Student Manager");
        this.studentManager = studentManager;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        field = new JTextField();

        JButton btn = new JButton("submit");



        btn.setActionCommand("myButton");
        btn.addActionListener(this);

        btn.setAlignmentX(Component.CENTER_ALIGNMENT);



        //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        JLabel label = new JLabel("Time to manage some students!");
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        add(label);


        JTextArea jTextArea = new JTextArea();
        jTextArea.setRows(15);
        jTextArea.setColumns(5);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);

        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        add(jScrollPane);
        //I got this idea from <https://www.codejava.net/java-se/swing/redirect-standard-output-streams-to-jtextarea>
        PrintStream printStream = new PrintStream(new CustomOutputStream(jTextArea));


        System.setOut(printStream);
        System.setErr(printStream);


        add(field);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public synchronized void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            action = field.getText();
            field.setText("");
            studentManager.setAction(action);
            //I got this idea from https://www.journaldev.com/1037/java-thread-wait-notify-and-notifyall-example
            synchronized (studentManager){
                studentManager.notify();
            }
        }

    }


    public static void main(String[] args) throws IOException, JSONException, InterruptedException {
        StudentManager sM = new StudentManager();
        new GUI(sM);
        sM.run();

    }
}
