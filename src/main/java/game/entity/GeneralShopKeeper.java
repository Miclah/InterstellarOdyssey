package game.entity;

import game.state.GeneralManager;
import game.things.Item;
import game.things.items.ColonyStarterKit;
import game.things.items.ResourceExtractor;
import game.things.items.ResourceProbe;
import game.things.items.Teleporter;
import game.things.items.Terraformer;
import game.things.items.QuantumCommunicator;
import game.things.items.NanobotRepairSystem;
import game.things.items.AIAssistant;
import game.things.items.MiningAxe;

/**
 * General Shopkeeper who sells items
 */
public class GeneralShopKeeper extends ShopKeeper {

    /**
     * Instantiates a new General shopkeeper.
     *
     * @param worldX  the world x
     * @param worldY  the world y
     * @param manager the manager
     */
    public GeneralShopKeeper(int worldX, int worldY, GeneralManager manager) {
        super(worldX, worldY, "General Store", "npc/general/shop/shop", 50, manager);
    }

    /**
     * Initializes shop with specified things, in this case items
     */
    @Override
    public void initializeShop() {
        Item[] itemsToAdd = {new MiningAxe(), new ColonyStarterKit(), new NanobotRepairSystem(), new ResourceProbe(), new ResourceExtractor(), new Teleporter(), new QuantumCommunicator(), new AIAssistant(), new Terraformer()};

        for (Item item : itemsToAdd) {
            super.addItem(item);
        }
    }
}
