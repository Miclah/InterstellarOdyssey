package game.entity;

import game.entity.interfazy.Interactible;
import game.gui.Shop;
import game.state.GeneralManager;
import game.things.Inventory;
import game.things.Thing;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 * Creates a shopkeeper with an inventory
 */
public abstract class ShopKeeper extends NPC implements Interactible {

    /**
     * The Discount.
     */
    private double discount;
    /**
     * The Sale.
     */
    private boolean sale;
    /**
     * The Inventory.
     */
    private Inventory<Thing> inventory;
    /**
     * The Interaction circle.
     */
    private Circle interactionCircle;
    /**
     * The Shop.
     */
    private final Shop shop;

    /**
     * Instantiates a new Shopkeeper.
     *
     * @param worldX      the world x
     * @param worldY      the world y
     * @param name        the name
     * @param pathToImage the path to image
     * @param relation    the relation
     * @param manager     the manager
     */
    public ShopKeeper(int worldX, int worldY, String name, String pathToImage, int relation, GeneralManager manager) {
        super(worldX, worldY, name, pathToImage, "SHOPKEEPER", relation, 1, manager);
        this.discount = 0;
        this.sale = false;
        this.inventory = new Inventory<>();
        this.shop = new Shop(manager.getPlayer().getCurrency(), manager.getKeyManager());
        this.createInteractionCircle();
        this.initializeShop();
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public double getDiscount() {
        return this.discount;
    }

    /**
     * Is sale boolean.
     *
     * @return the boolean
     */
    public boolean isSale() {
        return this.sale;
    }

    /**
     * Sets sale.
     *
     * @param sale the sale
     */
    public void setSale(boolean sale) {
        this.sale = sale;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(double discount) {
        if (discount < 0) {
            this.discount = 0;
        } else if (discount > 100) {
            this.discount = 100;
        } else {
            this.discount = discount;
        }
    }

    /**
     * Adds item to shopkeepers inventory
     *
     * @param thing the thing
     */
    public void addItem(Thing thing) {
        if (!this.inventory.getInventory().contains(thing)) {
            this.inventory.addItem(thing);
        }
    }

    /**
     * Creates a circle around shopkeeper to show to the player
     * that he is in close enough distance to interact with him
     */
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

    /**
     * Interface that gets called 60x per second to update the circles position
     *
     * @param canvas the canvas
     * @param player the player
     */
    @Override
    public void showZone(Canvas canvas, Player player) {
        double centerX = this.getWorldX() - player.getWorldX() + canvas.getWidth() / 2;
        double centerY = this.getWorldY() - player.getWorldY() + canvas.getHeight() / 2;

        this.interactionCircle.setCenterX(centerX);
        this.interactionCircle.setCenterY(centerY);
        this.interactionCircle.setVisible(true);
    }

    /**
     * Gets called once the player presses
     * SPACE to interact with a shopkeeper
     */
    @Override
    public void interact() {
        this.shop.displayShop(this.inventory.getInventory());
    }

    /**
     * Gets interaction circle.
     *
     * @return the interaction circle
     */
    public Circle getInteractionCircle() {
        return this.interactionCircle;
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public Inventory<Thing> getInventory() {
        return this.inventory;
    }

    /**
     * Abstract method to intialize shop with
     * items specified by subclasses of shopkeeper
     */
    public abstract void initializeShop();
}
