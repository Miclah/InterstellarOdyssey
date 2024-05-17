package game.entity;


import game.state.GeneralManager;

public class GeneralShopKeeper extends ShopKeeper {

    public GeneralShopKeeper(int worldX, int worldY, String pathToImage, GeneralManager manager) {
        super(worldX, worldY, "General Store", pathToImage, 100, manager);
    }
}
