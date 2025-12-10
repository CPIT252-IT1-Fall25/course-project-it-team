package sa.edu.kau.fcit.cpit252.project.CORE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RecordFilter {

    public static String getRecordsByDate(int year, int month, Integer day) {
        String fileName = "records.txt";
        StringBuilder sb = new StringBuilder();

        String targetPrefix = String.format("%04d/%02d", year, month);
        String targetDate = (day == null) ? null :
                String.format("%04d/%02d/%02d", year, month, day);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            boolean shouldPrint = false;
            boolean foundAny = false;

            while ((line = br.readLine()) != null) {

                if (line.contains("Summary (")) {
                    shouldPrint = false;

                    int start = line.indexOf('(');
                    int end = line.indexOf(')', start + 1);

                    if (start != -1 && end != -1) {
                        String inside = line.substring(start + 1, end).trim();

                        if (inside.length() >= 10) {
                            String dateDay = inside.substring(0, 10);
                            String datePrefix = inside.substring(0, 7);

                            if (day == null) {
                                if (datePrefix.equals(targetPrefix)) {
                                    shouldPrint = true;
                                    foundAny = true;
                                }
                            }
                            else {
                                if (dateDay.equals(targetDate)) {
                                    shouldPrint = true;
                                    foundAny = true;
                                }
                            }
                        }
                    }
                }

                if (shouldPrint) {
                    sb.append(line).append(System.lineSeparator());
                }
            }

            if (!foundAny) {
                if (day == null)
                    sb.append("No records found for ").append(targetPrefix).append(System.lineSeparator());
                else
                    sb.append("No records found for ").append(targetDate).append(System.lineSeparator());
            }

        } catch (IOException e) {
            sb.append("Error reading records file: ").append(e.getMessage())
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}

