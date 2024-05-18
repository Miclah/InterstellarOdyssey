package game.things.items;

import game.things.Item;

public class ResourceExtractor extends Item {
    private double radius;

    public ResourceExtractor() {
        super("Resource Extractor", "Automatically gather nearby resources while mining.", 1100);
        this.radius = 5.0;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}