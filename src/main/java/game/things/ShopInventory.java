package game.things;

import java.util.ArrayList;
import java.util.Collections;

public class ShopInventory {
    private ArrayList<Thing> inventory;

    public ShopInventory() {
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Thing> getInventory() {
        return (ArrayList<Thing>) Collections.unmodifiableList(this.inventory);
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
