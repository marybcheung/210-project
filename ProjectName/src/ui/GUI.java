package ui;


import model.Student;
import model.StudentManager;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GUI extends JFrame implements ActionListener{

    public String getAction() {
        return action;
    }

    private String action;
    private List<JButton> labels = new ArrayList<>();
    private JTextField field;
    public GUI()
    {
        super("Student Manager");
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

        add(label);

        //https://www.codejava.net/java-se/swing/redirect-standard-output-streams-to-jtextarea
        JTextArea jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);
        add(jTextArea);
        PrintStream printStream = new PrintStream(new CustomOutputStream(jTextArea));
        System.setOut(printStream);
        System.setErr(printStream);


        for (JButton l: labels) {
            l.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(l);
        }

        add(field);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            action = field.getText();
        }

    }

    public static void main(String[] args) throws IOException, JSONException {
        new GUI();
        new StudentManager().run();
    }
}
