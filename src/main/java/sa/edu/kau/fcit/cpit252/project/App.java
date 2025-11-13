package sa.edu.kau.fcit.cpit252.project;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter monthly salary: ");
        int salary = readNonNegativeInt(sc);

        System.out.println("\nEnter allocations (amounts) for each category:");
        int bills          = readCategory(sc, "Bills");
        int food           = readCategory(sc, "Food");
        int transportation = readCategory(sc, "Transportation");
        int entertainment  = readCategory(sc, "Entertainment");
        int shopping       = readCategory(sc, "Shopping");
        int savings        = readCategory(sc, "Savings");

        DailyRecord record = new DailyRecord.Builder()
                .salary(salary)
                .bills(bills)
                .food(food)
                .transportation(transportation)
                .entertainment(entertainment)
                .shopping(shopping)
                .savings(savings)
                .build();

        record.printSummary();
        sc.close();
    }

    private static int readCategory(Scanner sc, String name) {
        System.out.print(name + ": ");
        return readNonNegativeInt(sc);
    }

    private static int readNonNegativeInt(Scanner sc) {
        while (true) {
            String in = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(in);
                if (val < 0) {
                    System.out.print("Please enter a non-negative number: ");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }


    }
}