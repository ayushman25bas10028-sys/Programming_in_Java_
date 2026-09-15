import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main application interface loop managing execution, inputs, and fleet state.
 */
public class MainApp {
    private static final List<Rocket> fleet = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedDefaultRockets();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Select an option [1-4]: ", 1, 4);

            switch (choice) {
                case 1 -> viewFleet();
                case 2 -> addRocket();
                case 3 -> calculateDeltaV();
                case 4 -> {
                    running = false;
                    System.out.println("Exiting Rocket Delta-V Calculator. Safe launch!");
                }
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- ROCKET STAGE DELTA-V CALCULATOR ---");
        System.out.println("1. View Registered Launch Vehicles");
        System.out.println("2. Add New Rocket Configuration");
        System.out.println("3. Calculate Mission Delta-V & Orbit Check");
        System.out.println("4. Exit");
    }

    private static void viewFleet() {
        System.out.println("\n--- REGISTERED LAUNCH VEHICLES ---");
        if (fleet.isEmpty()) {
            System.out.println("No rockets in system.");
            return;
        }
        for (Rocket r : fleet) {
            r.displaySummary();
        }
    }

    private static void addRocket() {
        System.out.println("\n--- ADD NEW ROCKET ---");
        System.out.print("Enter Rocket ID (e.g., R-F9): ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Rocket Name: ");
        String name = scanner.nextLine().trim();
        double maxPayload = readDouble("Enter Max Payload Capacity (kg): ", 0);

        int stageCount = readInt("Enter Number of Stages [1-4]: ", 1, 4);
        List<Stage> stages = new ArrayList<>();

        for (int i = 1; i <= stageCount; i++) {
            System.out.printf("Enter Stage %d Dry Mass (kg): ", i);
            double dry = readDouble("", 1);
            System.out.printf("Enter Stage %d Propellant Mass (kg): ", i);
            double prop = readDouble("", 1);
            System.out.printf("Enter Stage %d Specific Impulse Isp (sec): ", i);
            double isp = readDouble("", 1);
            stages.add(new Stage(i, dry, prop, isp));
        }

        fleet.add(new Rocket(id, name, maxPayload, stages));
        System.out.println("Rocket added successfully!");
    }

    private static void calculateDeltaV() {
        System.out.print("\nEnter Rocket ID for Calculation: ");
        String id = scanner.nextLine().trim();

        Rocket match = null;
        for (Rocket r : fleet) {
            if (r.getRocketId().equalsIgnoreCase(id)) {
                match = r;
                break;
            }
        }

        if (match == null) {
            System.out.println("Error: Rocket ID not found.");
            return;
        }

        double payload = readDouble("Enter Mission Payload Mass (kg): ", 0);
        DeltaVCalculator.computeAndPrintDeltaV(match, payload);
    }

    private static void seedDefaultRockets() {
        List<Stage> saturnV = new ArrayList<>();
        saturnV.add(new Stage(1, 130000, 2160000, 263));
        saturnV.add(new Stage(2, 40100, 443000, 421));
        saturnV.add(new Stage(3, 13500, 109000, 421));
        fleet.add(new Rocket("SAT-V", "Saturn V", 45000, saturnV));
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                scanner.nextLine();
                if (val >= min && val <= max) return val;
            } else {
                scanner.nextLine();
            }
            System.out.printf("Please enter a number between %d and %d: ", min, max);
        }
    }

    private static double readDouble(String prompt, double min) {
        while (true) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double val = scanner.nextDouble();
                scanner.nextLine();
                if (val >= min) return val;
            } else {
                scanner.nextLine();
            }
            System.out.print("Please enter a valid positive decimal number: ");
        }
    }
}