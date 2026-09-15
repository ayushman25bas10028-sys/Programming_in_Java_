import java.util.List;

/**
 * Model class representing a rocket vehicle containing one or more stages.
 */
public class Rocket {
    private final String rocketId;
    private final String name;
    private final double maxPayloadKg;
    private final List<Stage> stages;

    public Rocket(String rocketId, String name, double maxPayloadKg, List<Stage> stages) {
        this.rocketId = rocketId;
        this.name = name;
        this.maxPayloadKg = maxPayloadKg;
        this.stages = stages;
    }

    public String getRocketId() { return rocketId; }
    public String getName() { return name; }
    public double getMaxPayloadKg() { return maxPayloadKg; }
    public List<Stage> getStages() { return stages; }

    public void displaySummary() {
        System.out.printf("ID: %-8s | Name: %-16s | Stages: %d | Max Payload: %.1f kg\n",
                rocketId, name, stages.size(), maxPayloadKg);
    }
}