package game.things.items;

import game.things.Item;

/**
 * Allows the player to start a colony on a habitable planet
 */
public class ColonyStarterKit extends Item {
    /**
     * Instantiates a new Colony starter kit.
     */
    public ColonyStarterKit() {
        super("Colony Starter Kit", "Establish a new colony on a discovered planet.", 400, "textures/things/items/colony.png");
    }
}