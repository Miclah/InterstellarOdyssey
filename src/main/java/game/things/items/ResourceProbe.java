package game.things.items;

import game.things.Item;

/**
 * Scans the planet for its mineral composition and wealth
 */
public class ResourceProbe extends Item {
    /**
     * Instantiates a new Resource probe.
     */
    public ResourceProbe() {
        super("Resource probe", "Deploy a probe to gather data on planetary resources.", 2500, "textures/things/items/resource_probe.png");
    }
}