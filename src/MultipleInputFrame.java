import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultipleInputFrame extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton submitButton;

    public MultipleInputFrame() {
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = textField1.getText();
                String input2 = textField2.getText();

                JOptionPane.showMessageDialog(null, "Input 1: " + input1 + "\nInput 2: " + input2);

            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Enter Goal: "));
        panel.add(textField1);
        panel.add(new JLabel("Enter Text 2: "));
        panel.add(textField2);
        panel.add(submitButton);

        this.add(panel);
        this.setTitle("Multiple Input Example");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }}