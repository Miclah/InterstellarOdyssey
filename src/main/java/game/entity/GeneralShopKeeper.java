package game.entity;


import game.state.GeneralManager;

public class GeneralShopKeeper extends ShopKeeper {

    public GeneralShopKeeper(int worldX, int worldY, GeneralManager manager) {
        super(worldX, worldY, "General Store", "npc/general/shop/shop", 50, manager);
    }
}
