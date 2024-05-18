package game.things.items;

import game.things.Item;

public class NanobotRepairSystem extends Item {
    private double repairRate;

    public NanobotRepairSystem() {
        super("Nanobot repair system", "Self-repair your equipment with advanced nanobots.", 1500);
        this.repairRate = 1.0;
    }

    public double getRepairRate() {
        return this.repairRate;
    }

    public void setRepairRate(double repairRate) {
        this.repairRate = repairRate;
    }
}