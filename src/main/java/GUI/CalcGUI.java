package GUI;

import Logic.CalcLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private JFrame frame;
    private JTextField textField;
    private JButton[] digitButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[11];
    private JButton addButton,subButton,mulButton,divButton;
    private JButton decButton,equButton,delButton, clrButton, negButton;
    private JPanel panel;
    private JButton bracketLeft, bracketRight;
    private CalcLogic calcLogic;

    private String expression;

    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    CalcGUI(){
        Thread.setDefaultUncaughtExceptionHandler(this);
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

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
        negButton = new JButton("(-)");
        bracketLeft = new JButton("(");
        bracketRight = new JButton(")");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = bracketLeft;
        functionButtons[10] = bracketRight;

        for (int i = 0; i < 11; i++) {
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
        bracketLeft.setBounds(0, 430, 50, 50);
        bracketRight.setBounds(350, 430, 50, 50);
        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

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

        frame.add(bracketLeft);
        frame.add(bracketRight);
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);


        calcLogic = new CalcLogic(textField.getText());
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
        Object obj = e.getSource();

        for (int i = 0; i < 10; i++) {
            if (obj == digitButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (obj == decButton){
            textField.setText(textField.getText().concat("."));
        } else if (obj == addButton){
            textField.setText(textField.getText().concat(String.valueOf(addButton.getText())));
        } else if (obj == subButton){
            textField.setText(textField.getText().concat(String.valueOf(subButton.getText())));
        } else if (obj == mulButton){
            textField.setText(textField.getText().concat(String.valueOf(mulButton.getText())));
        } else if (obj == divButton){
            textField.setText(textField.getText().concat(String.valueOf(divButton.getText())));
        } else if (obj == equButton){
            textField.setText(textField.getText().concat(String.valueOf(equButton.getText())));
        } else if (obj == clrButton){
            textField.setText("");
        } else if (obj == bracketLeft){
            textField.setText(textField.getText().concat(String.valueOf(bracketLeft.getText())));
        } else if (obj == bracketRight){
            textField.setText(textField.getText().concat(String.valueOf(bracketRight.getText())));
        } else if (obj == delButton){
            String temp = textField.getText();
            textField.setText("");
            for (int i = 0; i <temp.length() - 1 ; i++) {
                textField.setText(textField.getText()+temp.charAt(i));
            }
        } else if (obj == negButton){
            double temp = Double.parseDouble(textField.getText());
            temp*=-1;
            textField.setText(String.valueOf(temp));
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        String message;
        if (stackTraceElements.length == 0){
            message = "zero elements in stacktrace";
        }else{
            message = e.getClass().getCanonicalName() +
                    ": " + e.getMessage() + "\n" +
                    "\t at "  + stackTraceElements[0];
        }

        JOptionPane.showMessageDialog(this, message);
        System.exit(1);
    }
}
