package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabelChanger extends JFrame implements ActionListener{
    private JLabel label;
    private JTextField field;
    public LabelChanger()
    {
        super("Student Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JButton btn1 = new JButton("add to class-list");
        JButton btn2 = new JButton("assign homework");

        btn1.setActionCommand("add");
        btn2.setActionCommand("assign");

        btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn2.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        label = new JLabel("What would you like to do?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
        add(btn1);
        add(btn2);
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
