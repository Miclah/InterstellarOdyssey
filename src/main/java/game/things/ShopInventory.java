package game.things;

import java.util.ArrayList;

public class ShopInventory {
    private ArrayList<Thing> inventory;

    public ShopInventory() {
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Thing> getInventory() {
        return this.inventory; // vytvorit novy arraylist inak porusi zapuzdrenie?
    }

    public void addItem(Thing thing) {
        if (!this.inventory.contains(thing)) {
            this.inventory.add(thing);
        }
    }

    public void removeItem(Thing thing) {
        this.inventory.remove(thing);
    }
}
