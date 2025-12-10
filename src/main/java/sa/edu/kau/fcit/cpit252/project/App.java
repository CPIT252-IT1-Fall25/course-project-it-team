package sa.edu.kau.fcit.cpit252.project;

import sa.edu.kau.fcit.cpit252.project.GUI.MainMenuGUI;

import javax.swing.*;

public class App {
    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> {
            MainMenuGUI menu = new MainMenuGUI();
            menu.setVisible(true);
        });

        System.setProperty("swing.defaultlaf", "javax.swing.plaf.metal.MetalLookAndFeel");
    }
}