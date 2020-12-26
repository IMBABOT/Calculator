package GUI;

import javax.swing.*;
import java.awt.*;

public class CalcGUI extends JFrame {

    private final int WIDTH = 250;
    private final int HEIGHT = 250;
    private JPanel mainPanel  = new JPanel();

    CalcGUI(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width /2 - 250, dimension.height / 2 - 250, WIDTH, HEIGHT);
        mainPanel.setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel actions = new JPanel();



        textPanel.add(new JLabel("0"));
        buttons.setLayout(new GridLayout(4,4));
        actions.setLayout(new GridLayout(0,1));

        buttons.add(new JButton("7"));
        buttons.add(new JButton("8"));
        buttons.add(new JButton("9"));
        buttons.add(new JButton("4"));
        buttons.add(new JButton("5"));
        buttons.add(new JButton("6"));
        buttons.add(new JButton("1"));
        buttons.add(new JButton("2"));
        buttons.add(new JButton("3"));
        buttons.add(new JButton("0"));
        buttons.add(new JButton("."));
        buttons.add(new JButton("="));

        actions.add(new JButton("+"));
        actions.add(new JButton("-"));
        actions.add(new JButton("*"));
        actions.add(new JButton("/"));



        mainPanel.add(buttons, BorderLayout.CENTER);
        mainPanel.add(textPanel, BorderLayout.NORTH);
        mainPanel.add(actions, BorderLayout.EAST);

        this.getContentPane().add(mainPanel);

        setTitle("Calculator");
        this.setResizable(false);
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalcGUI();
            }
        });
    }
}
