package game.things.items;

import game.things.Item;

/**
 * Instantly teleports player back to Earth from anywhere in the cosmos
 */
public class Teleporter extends Item {
    /**
     * Instantiates a new Teleporter.
     */
    public Teleporter() {
        super("Teleporter", "Instantly travel back to your base on Earth.", 5000, "textures/things/items/teleporter.png");
    }
}