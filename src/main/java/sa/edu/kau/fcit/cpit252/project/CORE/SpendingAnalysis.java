package sa.edu.kau.fcit.cpit252.project.CORE;

import java.util.Map;

public class SpendingAnalysis {

    public static String analyze(int bills, int food, int transportation,
                                 int entertainment, int shopping) {

        Map<String, Integer> map = Map.of(
                "Bills", bills,
                "Food", food,
                "Transportation", transportation,
                "Entertainment", entertainment,
                "Shopping", shopping
        );

        int total = map.values().stream().mapToInt(i -> i).sum();

        String highest = map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        StringBuilder sb = new StringBuilder("Spending Analysis:\n");

        for (var e : map.entrySet()) {
            int percent = total == 0 ? 0 : (e.getValue() * 100 / total);
            sb.append(e.getKey()).append(": ")
                    .append(percent).append("%\n");
        }

        sb.append("Highest spending category: ").append(highest);

        return sb.toString();
    }
}
