package game.things;

import game.things.items.ColonyStarterKit;
import game.things.items.ResourceExtractor;
import game.things.items.ResourceProbe;
import game.things.items.Teleporter;
import game.things.items.Terraformer;
import game.things.items.QuantumCommunicator;
import game.things.items.NanobotRepairSystem;
import game.things.items.AIAssistant;
import game.things.items.MiningAxe;

import java.util.ArrayList;
import java.util.Collections;

public class ShopInventory {
    private ArrayList<Thing> inventory;

    public ShopInventory() {
        this.inventory = new ArrayList<>();
        this.initializeShopInventory();
    }

    public ArrayList<Thing> getInventory() {
        return (ArrayList<Thing>)Collections.unmodifiableList(this.inventory);
    }

    public void addItem(Thing thing) {
        if (!this.inventory.contains(thing)) {
            this.inventory.add(thing);
        }
    }

    private void initializeShopInventory() {
        this.inventory.add(new MiningAxe());
        this.inventory.add(new ColonyStarterKit());
        this.inventory.add(new NanobotRepairSystem());
        this.inventory.add(new ResourceProbe());
        this.inventory.add(new ResourceExtractor());
        this.inventory.add(new Teleporter());
        this.inventory.add(new QuantumCommunicator());
        this.inventory.add(new AIAssistant());
        this.inventory.add(new Terraformer());
    }

    public void removeItem(Thing thing) {
        this.inventory.remove(thing);
    }
}
