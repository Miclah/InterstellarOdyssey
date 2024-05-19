package game.things;

import java.util.ArrayList;
import game.things.Thing;

public class Inventory<T extends Thing> {
    private ArrayList<T> inventory;

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public ArrayList<T> getInventory() {
        return new ArrayList<>(this.inventory);
    }

    public void addItem(T thing) {
        this.inventory.add(thing);
    }

    public void removeItem(T thing) {
        this.inventory.remove(thing);
    }
}
