package game.entity;

import game.entity.interfazy.Interactible;
import game.gui.Shop;
import game.state.GeneralManager;
import game.things.Inventory;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public abstract class ShopKeeper extends NPC implements Interactible {

    private double discount;
    private boolean sale;
    private Inventory inventory;
    private Circle interactionCircle;
    private Shop shop;

    public ShopKeeper(int worldX, int worldY, String name, String pathToImage, int relation, GeneralManager manager) {
        super(worldX, worldY, name, pathToImage, "SHOPKEEPER", relation, 1, manager);
        this.discount = 0;
        this.sale = false;
        this.inventory = new Inventory ();
        this.shop = new Shop(manager.getPlayer().getCurrency(), manager.getKeyManager());
        this.createInteractionCircle();
        this.initializeShop();
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

    private void createInteractionCircle() {
        this.interactionCircle = new Circle(super.getWorldX(), super.getWorldY(), 100);

        // Source: https://www.tutorialspoint.com/javafx/javafx_radial_gradient_pattern.htm
        RadialGradient gradient = new RadialGradient(
                0,
                0.1,
                0.5,
                0.5,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(255, 255, 255, 0.8)),
                new Stop(1, Color.rgb(0, 0, 0, 0.2))
        );

        this.interactionCircle.setFill(gradient);
        this.interactionCircle.setStroke(Color.rgb(255, 255, 255, 0.6));
        this.interactionCircle.setStrokeWidth(3);
        this.interactionCircle.setVisible(false);
    }

    @Override
    public void showZone(Canvas canvas, Player player) {
        double centerX = this.getWorldX() - player.getWorldX() + canvas.getWidth() / 2;
        double centerY = this.getWorldY() - player.getWorldY() + canvas.getHeight() / 2;

        this.interactionCircle.setCenterX(centerX);
        this.interactionCircle.setCenterY(centerY);
        this.interactionCircle.setVisible(true);
    }

    @Override
    public void interact() {
        this.shop.displayShop(this.inventory.getInventory());
    }

    public Circle getInteractionCircle() {
        return this.interactionCircle;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public abstract void initializeShop();
}
