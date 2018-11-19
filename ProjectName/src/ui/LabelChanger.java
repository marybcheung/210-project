package ui;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LabelChanger extends JFrame implements ActionListener{

    JLabel label;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;

    private List<JLabel> labels = new ArrayList<>();
    private JTextField field;
    public LabelChanger()
    {
        super("Student Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        field = new JTextField();

        JButton btn = new JButton("submit");



        btn.setActionCommand("myButton");

        btn.setAlignmentX(Component.CENTER_ALIGNMENT);



        //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        label = new JLabel("What would you like to do?");
        label1 = new JLabel("[1] add to class-list");
        label2 = new JLabel("[2] assign homework");
        label3 = new JLabel("[3] record marks");
        label4 = new JLabel("[4] calculate student grades");
        label5 = new JLabel("[5] show class-list alerts");

        labels.add(label);
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        labels.add(label5);

        for (JLabel l: labels) {
            l.setAlignmentX(Component.CENTER_ALIGNMENT);
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
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("add"))
        {
            label.setText(field.getText());
        }
    }
    public static void main(String[] args)
    {
        new LabelChanger();
    }
}
