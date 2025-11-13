package sa.edu.kau.fcit.cpit252.project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DailyRecord {
    private int bills, food, transportation, entertainment, shopping, savings, salary;


    private DailyRecord(Builder b) {
        this.bills = b.bills;
        this.food = b.food;
        this.transportation = b.transportation;
        this.entertainment = b.entertainment;
        this.shopping = b.shopping;
        this.savings = b.savings;
        this.salary = b.salary;
    }

    public static class Builder {
        private int bills, food, transportation, entertainment, shopping, savings, salary;

        public Builder salary(int v){
            this.salary = v;
            return this;
        }

        public Builder bills(int v){
            this.bills = v;
            return this;
        }

        public Builder food(int v){
            this.food = v;
            return this;
        }

        public Builder transportation(int v){
            this.transportation = v;
            return this;
        }

        public Builder entertainment(int v){
            this.entertainment = v;
            return this;
        }

        public Builder shopping(int v){
            this.shopping = v;
            return this;
        }

        public Builder savings(int v){
            this.savings = v;
            return this;
        }

        public DailyRecord build(){
            return new DailyRecord(this);
        }

    }

    public void printSummary() {
        int totalAllocated = bills + food + transportation + entertainment + shopping + savings;
        int remaining = salary - totalAllocated;

        if (remaining < 0 ){
            savings = savings + remaining;
            if (savings < 0) {
                System.out.println("[Warning] Allocations exceed salary by " + Math.abs(savings));
            }
            totalAllocated = salary;
            remaining = 0;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        //
        try {
            java.nio.file.Path cwd = java.nio.file.Paths.get("").toAbsolutePath();
            int next = 1;
            java.util.regex.Pattern p = java.util.regex.Pattern.compile("^note(\\d+)\\.txt$");
            try (java.util.stream.Stream<java.nio.file.Path> stream = java.nio.file.Files.list(cwd)) {
                for (java.nio.file.Path path : (Iterable<java.nio.file.Path>) stream::iterator) {
                    String name = path.getFileName().toString();
                    java.util.regex.Matcher m = p.matcher(name);
                    if (m.matches()) {
                        int n = Integer.parseInt(m.group(1));
                        if (n >= next) next = n + 1;
                    }
                }
            } catch (Exception ignore) {}
            //

            String filename = "note" + next + ".txt";
            try (java.io.PrintWriter out = new java.io.PrintWriter(filename, "UTF-8")) {
                out.println("========== Summary (" + now.format(fmt) + ") ==========");
                out.println("Salary: " + salary);
                out.println("Bills=" + bills);
                out.println("Food=" + food);
                out.println("Transportation=" + transportation);
                out.println("Entertainment=" + entertainment);
                out.println("Shopping=" + shopping);
                out.println("Savings=" + savings);
                out.println("-----------------------------");
                out.println("Total Allocated: " + totalAllocated);
                out.println("Remaining: " + remaining);
            }



        } catch (Exception e) {
            System.out.println("Error saving summary: " + e.getMessage());
        }
    }

}