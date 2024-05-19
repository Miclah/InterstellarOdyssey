package game.things.items;

import game.things.Item;

public class MiningAxe extends Item {
    private double miningSpeed;
    private double durability;

    public MiningAxe() {
        super("Mining axe", "Allows you to mine resources", 50, "textures/things/items/pickaxe.png");
        this.miningSpeed = 1.0;
        this.durability = 100.0;
    }

    public double getDurability() {
        return this.durability;
    }

    public void setDurability(double durability) {
        this.durability = Math.max(durability, 0);
    }

    public double getMiningSpeed() {
        return this.miningSpeed;
    }

    public void setMiningSpeed(double miningSpeed) {
        this.miningSpeed = miningSpeed;
    }
}
