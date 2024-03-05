import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TipCalculator extends JFrame {
    private JTextField priceField, tipField, peopleField;
    private JLabel resultLabel;
    private JButton calculateButton;

    public TipCalculator() {
        createView();
        setTitle("Tip Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.setPreferredSize(new Dimension(200, 210));

        panel.add(new JLabel("Price:"));
        priceField = new JTextField(10);
        panel.add(priceField);

        panel.add(new JLabel("Tip (%):"));
        tipField = new JTextField(10);
        panel.add(tipField);

        panel.add(new JLabel("People:"));
        peopleField = new JTextField(10);
        panel.add(peopleField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { calculateTip(); }
        });
        panel.add(calculateButton);

        resultLabel = new JLabel(" ");
        panel.add(resultLabel);
    }

    private void calculateTip() {
        try {
            double price = Double.parseDouble(priceField.getText());
            double tipPercent = Double.parseDouble(tipField.getText());
            int people = Integer.parseInt(peopleField.getText());

            double total = price * (1 + tipPercent / 100);
            double share = total / people;

            resultLabel.setText("Each person should pay: £" + String.format("%.2f", share));
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TipCalculator().setVisible(true);
            }
        });
    }
}
