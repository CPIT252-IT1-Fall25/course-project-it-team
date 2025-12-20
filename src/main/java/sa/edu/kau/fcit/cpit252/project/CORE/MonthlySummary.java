package sa.edu.kau.fcit.cpit252.project.CORE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MonthlySummary {

    public static String getMonthlySummary(int year, int month) {

        String fileName = "records.txt";
        String targetPrefix = String.format("%04d/%02d", year, month);

        int totalSalary = 0;
        int totalBills = 0;
        int totalFood = 0;
        int totalTransportation = 0;
        int totalEntertainment = 0;
        int totalShopping = 0;
        int totalSavings = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            boolean read = false;

            while ((line = br.readLine()) != null) {

                if (line.contains("Summary (")) {
                    read = false;

                    int start = line.indexOf('(');
                    int end = line.indexOf(')', start + 1);

                    if (start != -1 && end != -1) {
                        String date = line.substring(start + 1, start + 8);
                        if (date.equals(targetPrefix)) {
                            read = true;
                        }
                    }
                }

                if (read) {
                    if (line.startsWith("Salary:"))
                        totalSalary += extractValue(line);
                    else if (line.startsWith("Bills="))
                        totalBills += extractValue(line);
                    else if (line.startsWith("Food="))
                        totalFood += extractValue(line);
                    else if (line.startsWith("Transportation="))
                        totalTransportation += extractValue(line);
                    else if (line.startsWith("Entertainment="))
                        totalEntertainment += extractValue(line);
                    else if (line.startsWith("Shopping="))
                        totalShopping += extractValue(line);
                    else if (line.startsWith("Savings="))
                        totalSavings += extractValue(line);
                }
            }

        } catch (IOException e) {
            return "Error reading records file.";
        }

        return """
                ===== Monthly Summary (%04d/%02d) =====
                Total Salary: %d
                Bills: %d
                Food: %d
                Transportation: %d
                Entertainment: %d
                Shopping: %d
                Savings: %d
                -----------------------------------
                Total Expenses: %d
                ===================================
                """.formatted(
                year, month,
                totalSalary,
                totalBills,
                totalFood,
                totalTransportation,
                totalEntertainment,
                totalShopping,
                totalSavings,
                (totalBills + totalFood + totalTransportation +
                        totalEntertainment + totalShopping)
        );
    }

    private static int extractValue(String line) {
        return Integer.parseInt(line.replaceAll("[^0-9]", ""));
    }
}
