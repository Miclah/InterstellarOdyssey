package game.entity;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneralShopKeeper extends ShopKeeper {
    public GeneralShopKeeper(int worldX, int worldY, String pathToImage, HashMap<RelationshipType, String> dialogue) {
        super(worldX, worldY, "General Store", pathToImage, dialogue, 50);
    }

}
