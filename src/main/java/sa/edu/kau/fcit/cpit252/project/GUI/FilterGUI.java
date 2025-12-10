package sa.edu.kau.fcit.cpit252.project.GUI;

import sa.edu.kau.fcit.cpit252.project.CORE.RecordFilter;

import javax.swing.*;
import java.awt.*;

public class FilterGUI extends JFrame {

    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextArea resultArea;

    public FilterGUI() {
        setTitle("Filter Records By Date");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 8, 8));

        inputPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Month:"));
        monthField = new JTextField();
        inputPanel.add(monthField);

        inputPanel.add(new JLabel("Day: (optional)"));
        dayField = new JTextField();
        inputPanel.add(dayField);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton filterButton = new JButton("Show Records");
        buttonPanel.add(filterButton);
        add(buttonPanel, BorderLayout.SOUTH);

        filterButton.addActionListener(e -> onFilter());
    }

    private void onFilter() {

        String yearText = yearField.getText().trim();
        String monthText = monthField.getText().trim();
        String dayText = dayField.getText().trim();

        int year, month;

        try {
            year = Integer.parseInt(yearText);
            month = Integer.parseInt(monthText);
        } catch (Exception ex) {
            return;
        }

        Integer day = null;
        if (!dayText.isEmpty()) {
            try {
                day = Integer.parseInt(dayText);
            } catch (Exception ex) {
                day = null;
            }
        }

        String result = RecordFilter.getRecordsByDate(year, month, day);

        resultArea.setText(result);
    }
}
