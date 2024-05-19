package game.entity;

import game.state.GeneralManager;
import game.things.items.ColonyStarterKit;
import game.things.items.ResourceExtractor;
import game.things.items.ResourceProbe;
import game.things.items.Teleporter;
import game.things.items.Terraformer;
import game.things.items.QuantumCommunicator;
import game.things.items.NanobotRepairSystem;
import game.things.items.AIAssistant;
import game.things.items.MiningAxe;

public class GeneralShopKeeper extends ShopKeeper {

    public GeneralShopKeeper(int worldX, int worldY, GeneralManager manager) {
        super(worldX, worldY, "General Store", "npc/general/shop/shop", 50, manager);
    }

    @Override
    public void initializeShop() {
        super.getInventory().addItem(new MiningAxe());
        super.getInventory().addItem(new ColonyStarterKit());
        super.getInventory().addItem(new NanobotRepairSystem());
        super.getInventory().addItem(new ResourceProbe());
        super.getInventory().addItem(new ResourceExtractor());
        super.getInventory().addItem(new Teleporter());
        super.getInventory().addItem(new QuantumCommunicator());
        super.getInventory().addItem(new AIAssistant());
        super.getInventory().addItem(new Terraformer());
    }
}
