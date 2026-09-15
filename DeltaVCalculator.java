import java.util.List;

/**
 * Physics engine applying Tsiolkovsky's Rocket Equation: Delta-v = Isp * g0 * ln(m0 / mf)
 */
public class DeltaVCalculator {
    public static final double STANDARD_GRAVITY = 9.80665; // m/s²
    public static final double LEO_BENCHMARK_M_S = 9300.0; // ~9.3 km/s for Low Earth Orbit

    public static void computeAndPrintDeltaV(Rocket rocket, double actualPayloadKg) {
        System.out.println("\n================ ROCKET DELTA-V ANALYSIS REPORT ================");
        System.out.printf("Rocket Target : %s (ID: %s)\n", rocket.getName(), rocket.getRocketId());
        System.out.printf("Payload Mass  : %.1f kg (Structural Max: %.1f kg)\n", actualPayloadKg, rocket.getMaxPayloadKg());

        if (actualPayloadKg > rocket.getMaxPayloadKg()) {
            System.out.println("PAYLOAD STATUS: REJECTED [Exceeds structural payload limits!]");
            System.out.println("================================================================");
            return;
        }

        List<Stage> stages = rocket.getStages();
        double totalDeltaV = 0.0;
        double currentTotalMass = actualPayloadKg;

        for (Stage stage : stages) {
            currentTotalMass += stage.getTotalMassKg();
        }

        System.out.printf("Total Initial Mass (m0): %.1f kg\n\n", currentTotalMass);
        System.out.println("--- STAGE BURNOUT DETAILS ---");

        for (Stage stage : stages) {
            double initialMass = currentTotalMass;
            double finalMass = currentTotalMass - stage.getPropellantMassKg();

            double stageDeltaV = stage.getSpecificImpulseSec() * STANDARD_GRAVITY * Math.log(initialMass / finalMass);
            totalDeltaV += stageDeltaV;

            System.out.printf("Stage %d: Delta-v = %8.2f m/s | m0 = %9.1f kg | mf = %9.1f kg\n",
                    stage.getStageNumber(), stageDeltaV, initialMass, finalMass);

            // Jettison stage dry mass for subsequent calculations
            currentTotalMass = finalMass - stage.getDryMassKg();
        }

        System.out.println("----------------------------------------------------------------");
        System.out.printf("TOTAL CUMULATIVE DELTA-V: %.2f m/s (%.2f km/s)\n", totalDeltaV, totalDeltaV / 1000.0);
        System.out.printf("LEO Benchmark Required  : %.0f m/s\n", LEO_BENCHMARK_M_S);

        if (totalDeltaV >= LEO_BENCHMARK_M_S) {
            System.out.println("ORBITAL FEASIBILITY     : ORBITAL CAPABLE (LEO)");
        } else {
            System.out.printf("ORBITAL FEASIBILITY     : SUB-ORBITAL ONLY (Deficit: %.2f m/s)\n", (LEO_BENCHMARK_M_S - totalDeltaV));
        }
        System.out.println("================================================================");
    }
}