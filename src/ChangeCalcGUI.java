import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ChangeCalcGUI extends JFrame {
    private JTextField priceField, paidField;
    private JButton calculateButton;
    private JTextArea changeArea;
    public ChangeCalcGUI() {
        createview();
        setTitle("Change Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    private void createview() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(6,1,20,12));
        panel.setPreferredSize(new Dimension(250, 210));

        panel.add(new JLabel("Price:"));
        priceField = new JTextField(10);
        panel.add(priceField);

        panel.add(new JLabel("Paid:"));
        paidField = new JTextField(10);
        panel.add(paidField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { calculateChange(); }
        });
        panel.add(calculateButton);

        changeArea = new JTextArea(" ");
        changeArea.setEditable(false);
        panel.add(changeArea);
    }
    private void calculateChange() {
        double price, paid;
        StringBuilder text = new StringBuilder();
        try {
        price = Double.parseDouble(priceField.getText());
        paid = Double.parseDouble(paidField.getText());
            if (price > paid) {
                changeArea.setText("You haven't paid enough!");
            } else {
                TreeMap<NotesAndCoins, Integer> changeComposition = MainChange.calcChange(price, paid);
                for (NotesAndCoins n : changeComposition.keySet()) {
                    if (changeComposition.get(n) != 0) {
                        text.append(n.getName()).append(": ").append(changeComposition.get(n)).append(", ");
                    }
                }
                text.deleteCharAt(text.lastIndexOf(", "));
                changeArea.setText(String.valueOf(text));
            }
        } catch (NumberFormatException ex) {
            changeArea.setText("Invalid input. Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChangeCalcGUI().setVisible(true);
            }
        });
    }
}
