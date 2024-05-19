package game.things.items;

import game.things.Item;

/**
 * Automatically extracts resources around the player when he mines
 */
public class ResourceExtractor extends Item {
    /**
     * The Radius.
     */
    private double radius;

    /**
     * Instantiates a new Resource extractor.
     */
    public ResourceExtractor() {
        super("Resource Extractor", "Automatically gather nearby resources while mining.", 1100, "textures/things/items/resource_extractor.png");
        this.radius = 5.0;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Sets radius.
     *
     * @param radius the radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
}