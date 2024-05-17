package game.entity;

import game.state.GeneralManager;
import game.things.ShopInventory;
import javafx.scene.canvas.Canvas;

public abstract class ShopKeeper extends NPC implements Interactible{

    private double discount;
    private boolean sale;
    private ShopInventory inventory;

    public ShopKeeper(int worldX, int worldY, String name, String pathToImage, int relation, GeneralManager manager) {
        super(worldX, worldY, name, pathToImage, "SHOPKEEPER", relation, 1, manager);
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
    public void interact(Canvas canvas, Player player) {

    }
}
