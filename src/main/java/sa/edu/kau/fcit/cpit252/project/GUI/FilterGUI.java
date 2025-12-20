package sa.edu.kau.fcit.cpit252.project.GUI;

import sa.edu.kau.fcit.cpit252.project.CORE.RecordFilter;
import sa.edu.kau.fcit.cpit252.project.CORE.MonthlySummary;

import javax.swing.*;
import java.awt.*;

public class FilterGUI extends JFrame {

    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextArea resultArea;

    public FilterGUI() {
        setTitle("Filter Records By Date");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane())
                .setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 8, 8));

        inputPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Month:"));
        monthField = new JTextField();
        inputPanel.add(monthField);

        inputPanel.add(new JLabel("Day (optional):"));
        dayField = new JTextField();
        inputPanel.add(dayField);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton filterButton = new JButton("Show Records");
        JButton summaryButton = new JButton("Monthly Summary");

        buttonPanel.add(filterButton);
        buttonPanel.add(summaryButton);

        add(buttonPanel, BorderLayout.SOUTH);

        filterButton.addActionListener(e -> onFilter());
        summaryButton.addActionListener(e -> onMonthlySummary());
    }

    private void onFilter() {

        try {
            int year = Integer.parseInt(yearField.getText().trim());
            int month = Integer.parseInt(monthField.getText().trim());

            Integer day = null;
            if (!dayField.getText().trim().isEmpty()) {
                day = Integer.parseInt(dayField.getText().trim());
            }

            String result = RecordFilter.getRecordsByDate(year, month, day);
            resultArea.setText(result);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid year and month.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void onMonthlySummary() {

        try {
            int year = Integer.parseInt(yearField.getText().trim());
            int month = Integer.parseInt(monthField.getText().trim());

            String summary = MonthlySummary.getMonthlySummary(year, month);

            JOptionPane.showMessageDialog(
                    this,
                    summary,
                    "Monthly Summary",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid year and month.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
