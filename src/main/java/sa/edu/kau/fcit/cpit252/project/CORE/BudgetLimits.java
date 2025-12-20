package sa.edu.kau.fcit.cpit252.project.CORE;

import java.util.HashMap;
import java.util.Map;

public class BudgetLimits {

    private static final Map<String, Integer> limits = new HashMap<>();

    static {
        limits.put("Bills", 500);
        limits.put("Food", 800);
        limits.put("Transportation", 300);
        limits.put("Entertainment", 300);
        limits.put("Shopping", 400);
    }

    public static String check(int bills, int food, int transportation,
                               int entertainment, int shopping) {

        StringBuilder sb = new StringBuilder();

        if (bills > limits.get("Bills"))
            sb.append("Bills exceeded limit!\n");
        if (food > limits.get("Food"))
            sb.append("Food exceeded limit!\n");
        if (transportation > limits.get("Transportation"))
            sb.append("Transportation exceeded limit!\n");
        if (entertainment > limits.get("Entertainment"))
            sb.append("Entertainment exceeded limit!\n");
        if (shopping > limits.get("Shopping"))
            sb.append("Shopping exceeded limit!\n");

        return sb.toString();
    }
}
