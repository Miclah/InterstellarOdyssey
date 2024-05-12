package game.entity;

import game.things.ShopInventory;
import java.util.HashMap;

public abstract class ShopKeeper extends NPC implements Talkable{

    private double discount;
    private boolean sale;
    private ShopInventory inventory;

    public ShopKeeper(int worldX, int worldY, String name, String pathToImage, HashMap<RelationshipType, String> dialogue, int relation) {
        super(worldX, worldY, name, pathToImage, dialogue, relation);
        this.discount = 0;
        this.sale = false;
        this.inventory = new ShopInventory();
    }

    public double getDiscount() {
        return this.discount;
    }

    public boolean isSale() {
        return this.sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public void setDiscount(double discount) {
        if (discount < 0) {
            this.discount = 0;
        } else if (discount > 100) {
            this.discount = 100;
        } else {
            this.discount = discount;
        }
    }

    @Override
    public void talk() {

    }

    @Override
    public void interact() {
        super.interact();
    }
}
