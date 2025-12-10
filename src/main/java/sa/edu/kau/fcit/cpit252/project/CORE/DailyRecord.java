package sa.edu.kau.fcit.cpit252.project.CORE;

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


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");



        StringBuilder sb = new StringBuilder();
        sb.append("\n========== Summary (").append(now.format(fmt)).append(") ==========\n");
        sb.append("Salary: ").append(salary).append("\n");
        sb.append("Bills=").append(bills).append("\n");
        sb.append("Food=").append(food).append("\n");
        sb.append("Transportation=").append(transportation).append("\n");
        sb.append("Entertainment=").append(entertainment).append("\n");
        sb.append("Shopping=").append(shopping).append("\n");
        sb.append("Savings=").append(savings).append("\n");
        sb.append("-----------------------------\n");
        sb.append("Total Allocated: ").append(totalAllocated).append("\n");
        sb.append("Remaining: ").append(remaining).append("\n");

        NoteWriter nw = NoteWriter.getInstance();
        nw.addSummary(sb.toString());
        nw.save();

        BalanceManager sm = BalanceManager.getInstance();
        sm.addValues(savings, remaining);

    }
}
