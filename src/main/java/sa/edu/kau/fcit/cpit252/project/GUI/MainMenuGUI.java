package sa.edu.kau.fcit.cpit252.project.GUI;

import javax.swing.*;
import java.awt.*;

public class MainMenuGUI extends JFrame {

    public MainMenuGUI() {
        setTitle("Smart Expense Tracker - Main Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Smart Expense Tracker", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addRecordButton = new JButton("Add / Save Record");
        JButton filterButton = new JButton("Filter Records");

        buttonPanel.add(addRecordButton);
        buttonPanel.add(filterButton);

        add(buttonPanel, BorderLayout.CENTER);

        addRecordButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new AddRecordGUI().setVisible(true));
        });

        filterButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new FilterGUI().setVisible(true));
        });
    }

}
