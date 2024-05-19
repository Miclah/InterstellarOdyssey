package game.things;

import java.util.ArrayList;

/**
 * Inventory which can hold anything that is a subclass of Thing
 *
 * @param <T> the type parameter
 */
public class Inventory<T extends Thing> {
    /**
     * The Inventory.
     */
    private ArrayList<T> inventory;

    /**
     * Instantiates a new Inventory.
     */
    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Gets a copy of inventory.
     *
     * @return the inventory
     */
    public ArrayList<T> getInventory() {
        return new ArrayList<>(this.inventory);
    }

    /**
     * Adds item to the inventory.
     *
     * @param thing the thing
     */
    public void addItem(T thing) {
        this.inventory.add(thing);
    }

    /**
     * Removes item from inventory.
     *
     * @param thing the thing
     */
    public void removeItem(T thing) {
        this.inventory.remove(thing);
    }
}
