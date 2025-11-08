package org.example;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter monthly salary: ");
        int salary = sc.nextInt();

        System.out.println("\nEnter allocations (amounts) for each category:");
        int bills          = readCategory(sc, "Bills");
        int food           = readCategory(sc, "Food");
        int transportation = readCategory(sc, "Transportation");
        int entertainment  = readCategory(sc, "Entertainment");
        int shopping       = readCategory(sc, "Shopping");
        int savings        = readCategory(sc, "Savings");

        int totalAllocated = bills + food + transportation + entertainment + shopping + savings;
        int remaining = salary - totalAllocated;

        System.out.println("\n========== Summary ==========");
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

        sc.close();
    }

    private static int readCategory(Scanner sc, String name) {
        System.out.print(name + ": ");
        return sc.nextInt();
    }

}