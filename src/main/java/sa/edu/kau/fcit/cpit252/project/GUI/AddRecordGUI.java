package sa.edu.kau.fcit.cpit252.project.GUI;

import sa.edu.kau.fcit.cpit252.project.CORE.BalanceManager;
import sa.edu.kau.fcit.cpit252.project.CORE.DailyRecord;
import sa.edu.kau.fcit.cpit252.project.CORE.BudgetLimits;
import sa.edu.kau.fcit.cpit252.project.CORE.SpendingAnalysis;





import javax.swing.*;
import java.awt.*;

public class AddRecordGUI extends JFrame {

    private JTextField salaryField;
    private JTextField billsField;
    private JTextField foodField;
    private JTextField transportationField;
    private JTextField entertainmentField;
    private JTextField shoppingField;
    private JTextField savingsField;

    private JLabel recordTotalLabel;
    private JLabel recordRemainingLabel;
    private JLabel totalSavingsLabel;
    private JLabel totalRemainingLabel;

    public AddRecordGUI() {
        setTitle("Add / Save Record");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JPanel formPanel = new JPanel(new GridLayout(7, 2, 8, 8));

        formPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        formPanel.add(salaryField);

        formPanel.add(new JLabel("Bills:"));
        billsField = new JTextField();
        formPanel.add(billsField);

        formPanel.add(new JLabel("Food:"));
        foodField = new JTextField();
        formPanel.add(foodField);

        formPanel.add(new JLabel("Transportation:"));
        transportationField = new JTextField();
        formPanel.add(transportationField);

        formPanel.add(new JLabel("Entertainment:"));
        entertainmentField = new JTextField();
        formPanel.add(entertainmentField);

        formPanel.add(new JLabel("Shopping:"));
        shoppingField = new JTextField();
        formPanel.add(shoppingField);

        formPanel.add(new JLabel("Savings:"));
        savingsField = new JTextField();
        formPanel.add(savingsField);

        add(formPanel, BorderLayout.NORTH);


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1, 5, 5));

        recordTotalLabel = new JLabel("Record Total Allocated: -");
        recordRemainingLabel = new JLabel("Record Remaining: -");
        totalSavingsLabel = new JLabel("Total Savings (file): -");
        totalRemainingLabel = new JLabel("Total Remaining (file): -");

        BalanceManager bm = BalanceManager.getInstance();
        totalSavingsLabel.setText("Total Savings (file): " + bm.getTotalSavings());
        totalRemainingLabel.setText("Total Remaining (file): " + bm.getTotalRemaining());

        infoPanel.add(recordTotalLabel);
        infoPanel.add(recordRemainingLabel);
        infoPanel.add(totalSavingsLabel);
        infoPanel.add(totalRemainingLabel);

        add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Record");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);


        saveButton.addActionListener(e -> onSave());
        clearButton.addActionListener(e -> onClear());
    }

    private int safeParse(JTextField field) {
        String text = field.getText().trim();
        if (text.isEmpty()) return 0;

        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }


    private void onSave() {

        try {
            int salary = parseNonNegativeInt(salaryField, "Salary");
            int bills = parseNonNegativeInt(billsField, "Bills");
            int food = parseNonNegativeInt(foodField, "Food");
            int transportation = parseNonNegativeInt(transportationField, "Transportation");
            int entertainment = parseNonNegativeInt(entertainmentField, "Entertainment");
            int shopping = parseNonNegativeInt(shoppingField, "Shopping");
            int savings = parseNonNegativeInt(savingsField, "Savings");

            int totalAllocated = bills + food + transportation
                    + entertainment + shopping + savings;

            if (totalAllocated > salary) {
                throw new IllegalArgumentException("Expenses exceed salary!");
            }

            String warnings = BudgetLimits.check(
                    bills, food, transportation, entertainment, shopping);

            if (!warnings.isEmpty()) {
                JOptionPane.showMessageDialog(this, warnings,
                        "Budget Warning", JOptionPane.WARNING_MESSAGE);
            }

            DailyRecord record = new DailyRecord.Builder()
                    .salary(salary)
                    .bills(bills)
                    .food(food)
                    .transportation(transportation)
                    .entertainment(entertainment)
                    .shopping(shopping)
                    .savings(savings)
                    .build();

            record.printSummary();

            recordTotalLabel.setText("Record Total Allocated: " + totalAllocated);
            recordRemainingLabel.setText("Record Remaining: " + (salary - totalAllocated));

            BalanceManager bm = BalanceManager.getInstance();
            totalSavingsLabel.setText("Total Savings (file): " + bm.getTotalSavings());
            totalRemainingLabel.setText("Total Remaining (file): " + bm.getTotalRemaining());

            String analysis = SpendingAnalysis.analyze(
                    bills, food, transportation, entertainment, shopping);

            JOptionPane.showMessageDialog(this, analysis,
                    "Spending Analysis", JOptionPane.INFORMATION_MESSAGE);

            JOptionPane.showMessageDialog(this,
                    "Record saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private void onClear() {
        salaryField.setText("");
        billsField.setText("");
        foodField.setText("");
        transportationField.setText("");
        entertainmentField.setText("");
        shoppingField.setText("");
        savingsField.setText("");

        recordTotalLabel.setText("Record Total Allocated: -");
        recordRemainingLabel.setText("Record Remaining: -");
    }

    private int parseNonNegativeInt(JTextField field, String fieldName) throws NumberFormatException {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException(fieldName + " cannot be empty.");
        }
        int val;
        try {
            val = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(fieldName + " must be a valid number.");
        }
        if (val < 0) {
            throw new NumberFormatException(fieldName + " cannot be negative.");
        }
        return val;
    }
}