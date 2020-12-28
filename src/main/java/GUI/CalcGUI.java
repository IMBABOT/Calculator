package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcGUI extends JFrame implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] digitButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton,subButton,mulButton,divButton;
    JButton decButton,equButton,delButton, clrButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    double num1=0, num2=0, result=0;
    char operator;

    CalcGUI(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].addActionListener(this);
            digitButtons[i].setFont(myFont);
            digitButtons[i].setFocusable(false);
        }

        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(205, 430, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(digitButtons[1]);
        panel.add(digitButtons[2]);
        panel.add(digitButtons[3]);
        panel.add(addButton);
        panel.add(digitButtons[4]);
        panel.add(digitButtons[5]);
        panel.add(digitButtons[6]);
        panel.add(subButton);
        panel.add(digitButtons[7]);
        panel.add(digitButtons[8]);
        panel.add(digitButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(digitButtons[0]);
        panel.add(equButton);
        panel.add(divButton);


        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalcGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == digitButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource()==decButton){
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource()==addButton){
            textField.setText(textField.getText().concat(String.valueOf(addButton.getText())));
        }
        if (e.getSource()==subButton){
            textField.setText(textField.getText().concat(String.valueOf(subButton.getText())));
        }
        if (e.getSource()==mulButton){
            textField.setText(textField.getText().concat(String.valueOf(mulButton.getText())));
        }
        if (e.getSource()==divButton){
            textField.setText(textField.getText().concat(String.valueOf(divButton.getText())));
        }
        if (e.getSource()==equButton){
            textField.setText(textField.getText().concat(String.valueOf(equButton.getText())));

        }
    }
}
