package sa.edu.kau.fcit.cpit252.project;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BalanceManager {
    private static BalanceManager instance;
    private final String filename = "savings.txt";
    private int totalSavings = 0;
    private int totalRemaining = 0;

    private BalanceManager() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Saving:")) {
                    totalSavings = Integer.parseInt(line.replace("Saving:", "").trim());
                } else if (line.startsWith("Remaining:")) {
                    totalRemaining = Integer.parseInt(line.replace("Remaining:", "").trim());
                }
            }
        } catch (IOException ignored) {
        }
    }

    public static synchronized BalanceManager getInstance() {
        if (instance == null) instance = new BalanceManager();
        return instance;
    }

    public synchronized void addValues(int savings, int remaining) {

        totalSavings += savings;
        totalRemaining += remaining;

        if (totalRemaining < 0){
            totalSavings += totalRemaining;
            totalRemaining = 0;
        }

        saveToFile();
    }


    private void saveToFile() {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(filename, false), StandardCharsets.UTF_8))) {
            out.println("Saving: " + totalSavings);
            out.println("Remaining: " + totalRemaining);
        } catch (IOException e) {
            System.out.println("Error writing to " + filename + ": " + e.getMessage());
        }
    }

    public int getTotalSavings() {
        return totalSavings;
    }

    public int getTotalRemaining() {
        return totalRemaining;
    }
}
