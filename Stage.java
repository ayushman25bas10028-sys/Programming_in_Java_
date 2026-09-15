/**
 * Model class representing an individual rocket stage with mass properties and Isp.
 */
public class Stage {
    private final int stageNumber;
    private final double dryMassKg;
    private final double propellantMassKg;
    private final double specificImpulseSec;

    public Stage(int stageNumber, double dryMassKg, double propellantMassKg, double specificImpulseSec) {
        this.stageNumber = stageNumber;
        this.dryMassKg = dryMassKg;
        this.propellantMassKg = propellantMassKg;
        this.specificImpulseSec = specificImpulseSec;
    }

    public int getStageNumber() { return stageNumber; }
    public double getDryMassKg() { return dryMassKg; }
    public double getPropellantMassKg() { return propellantMassKg; }
    public double getSpecificImpulseSec() { return specificImpulseSec; }
    public double getTotalMassKg() { return dryMassKg + propellantMassKg; }

    public String toCsv() {
        return stageNumber + "," + dryMassKg + "," + propellantMassKg + "," + specificImpulseSec;
    }
}