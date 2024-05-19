package game.things.items;

import game.things.Item;

/**
 * An axe to mine minerals with
 */
public class MiningAxe extends Item {
    /**
     * The Mining speed.
     */
    private double miningSpeed;
    /**
     * The Durability.
     */
    private double durability;

    /**
     * Instantiates a new Mining axe.
     */
    public MiningAxe() {
        super("Mining axe", "Allows you to mine resources", 50, "textures/things/items/pickaxe.png");
        this.miningSpeed = 1.0;
        this.durability = 100.0;
    }

    /**
     * Gets durability.
     *
     * @return the durability
     */
    public double getDurability() {
        return this.durability;
    }

    /**
     * Sets durability.
     *
     * @param durability the durability
     */
    public void setDurability(double durability) {
        this.durability = Math.max(durability, 0);
    }

    /**
     * Gets mining speed.
     *
     * @return the mining speed
     */
    public double getMiningSpeed() {
        return this.miningSpeed;
    }

    /**
     * Sets mining speed.
     *
     * @param miningSpeed the mining speed
     */
    public void setMiningSpeed(double miningSpeed) {
        this.miningSpeed = miningSpeed;
    }
}
