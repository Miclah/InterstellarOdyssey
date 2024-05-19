package game.things.items;

import game.things.Item;

/**
 * Repairs items inside players inventory which have a durability attribute
 */
public class NanobotRepairSystem extends Item {
    /**
     * The Repair rate.
     */
    private double repairRate;

    /**
     * Instantiates a new Nanobot repair system.
     */
    public NanobotRepairSystem() {
        super("Nanobot repair system", "Self-repair your equipment with advanced nanobots.", 1500, "textures/things/items/nanobot.png");
        this.repairRate = 1.0;
    }

    /**
     * Gets repair rate.
     *
     * @return the repair rate
     */
    public double getRepairRate() {
        return this.repairRate;
    }

    /**
     * Sets repair rate.
     *
     * @param repairRate the repair rate
     */
    public void setRepairRate(double repairRate) {
        this.repairRate = repairRate;
    }
}