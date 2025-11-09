package sa.edu.kau.fcit.cpit252.project;

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
                System.out.close();
            }
            totalAllocated = salary;
            remaining = 0;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        System.out.println("\n========== Summary (" + now.format(fmt) + ") ==========");
        System.out.println("Salary: " + salary);
        System.out.println("Bills=" + bills);
        System.out.println("Food=" + food);
        System.out.println("Transportation=" + transportation);
        System.out.println("Entertainment=" + entertainment);
        System.out.println("Shopping=" + shopping);
        System.out.println("Savings=" + savings);
        System.out.println("-----------------------------");
        System.out.println("Total Allocated: " + totalAllocated);
        System.out.println("Remaining: " + remaining);

    }
}