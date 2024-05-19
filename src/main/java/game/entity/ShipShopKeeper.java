package game.entity;

import game.state.GeneralManager;
import game.things.Item;


/**
 * Shipkeeper who sells Spaceship related items
 */
public class ShipShopKeeper extends ShopKeeper {

    /**
     * Instantiates a new Shipkeeper.
     *
     * @param worldX  the world x
     * @param worldY  the world y
     * @param manager the manager
     */
    public ShipShopKeeper(int worldX, int worldY, GeneralManager manager) {
        super(worldX, worldY, "SpaceShips Store", "npc/general/shop/shop", 50, manager);
    }

    /**
     * Initializes shop with items specific to ShipKeeper
     */
    @Override
    public void initializeShop() {
        Item[] itemsToAdd = {};

        for (Item item : itemsToAdd) {
            super.addItem(item);
        }
    }
}
