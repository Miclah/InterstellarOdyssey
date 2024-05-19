package game.things.items;

import game.things.Item;

/**
 * Terraforms an inhabitable planet to a habitable one
 */
public class Terraformer extends Item {
    /**
     * Instantiates a new Terraformer.
     */
    public Terraformer() {
        super("Terraformer", "Transform harsh environments into habitable ones.", 6000, "textures/things/items/terraformer.png");
    }
}