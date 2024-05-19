package game.entity;

import game.state.GeneralManager;
import game.state.KeyManager;
import game.things.Inventory;
import game.things.Item;
import javafx.scene.Scene;

/**
 * Player class
 */
public class Player extends Entity {

    /**
     * The Screen x.
     */
    private final double screenX;
    /**
     * The Screen y.
     */
    private final double screenY;
    /**
     * The Key manager.
     */
    private final KeyManager keyManager;
    /**
     * The Currency.
     */
    private static int currency;
    /**
     * The Mining speed.
     */
    private double miningSpeed;
    /**
     * The Inventory.
     */
    private static Inventory<Item> inventory;

    /**
     * Instantiates a new Player.
     *
     * @param worldX      the world x
     * @param worldY      the world y
     * @param speed       the speed
     * @param name        the name
     * @param scene       the scene
     * @param keyManager  the key manager
     * @param manager     the manager
     * @param pathToImage the path to image
     */
    public Player(int worldX, int worldY, int speed, String name, Scene scene, KeyManager keyManager, GeneralManager manager, String pathToImage) {
        super(worldX, worldY, name, pathToImage, speed, manager);
        this.screenX = scene.getWidth() / 2 - 32;
        this.screenY = scene.getHeight() / 2 - 32;
        this.keyManager = keyManager;
        currency = 200;
        this.miningSpeed = 1.0;
        inventory = new Inventory<> ();
        scene.setOnKeyPressed(this.keyManager::handleKeyPressed);
        scene.setOnKeyReleased(this.keyManager::handleKeyReleased);

    }

    /**
     * Update method gets called 60x per second to make a player
     * character move into a specified direction
     */
    public void update() {
        if (this.keyManager.isMoving() && !this.keyManager.isPaused()) {
            super.setDirection(this.keyManager.getCurrentDirection());
            super.getManager().getCollision().check(this);
            this.move();
        }
    }

    /**
     * Overrides method from parent to not check for collisions
     * with other npcs, moves player into specified direction
     */
    @Override
    public void move() {
        if (super.getDirection() != null) {
            super.setCurrentSpeed(Math.min(super.getCurrentSpeed() + 0.1, super.getBaseSpeed()));

            double moveX = 0;
            double moveY = 0;

            switch (super.getDirection()) {
                case UP -> {
                    moveY = -super.getCurrentSpeed();
                }
                case DOWN -> {
                    moveY = super.getCurrentSpeed();
                }
                case LEFT -> {
                    moveX = -super.getCurrentSpeed();
                }
                case RIGHT -> {
                    moveX = super.getCurrentSpeed();
                }
                case UP_LEFT -> {
                    moveY = -super.getCurrentSpeed() / Math.sqrt(2);
                    moveX = -super.getCurrentSpeed() / Math.sqrt(2);
                }
                case UP_RIGHT -> {
                    moveY = -super.getCurrentSpeed() / Math.sqrt(2);
                    moveX = super.getCurrentSpeed() / Math.sqrt(2);
                }
                case DOWN_LEFT -> {
                    moveY = super.getCurrentSpeed() / Math.sqrt(2);
                    moveX = -super.getCurrentSpeed() / Math.sqrt(2);
                }
                case DOWN_RIGHT -> {
                    moveY = super.getCurrentSpeed() / Math.sqrt(2);
                    moveX = super.getCurrentSpeed() / Math.sqrt(2);
                }
            }

            super.setCollision(false);
            super.getManager().getCollision().check(this);
            if (!super.isCollision()) {
                super.setWorldX(super.getWorldX() + moveX);
                super.setWorldY(super.getWorldY() + moveY);
            }

            super.setSpriteCounter(super.getSpriteCounter() + 1);
            if (super.getSpriteCounter() > 15) {
                if (super.getSpriteNumber() == 1) {
                    super.setSpriteNumber(2);
                } else {
                    super.setSpriteNumber(1);
                }
                super.setSpriteCounter(0);
            }
            this.changeFrame();
        } else {
            super.setCurrentSpeed(Math.max(super.getCurrentSpeed() - 0.2, 0));
        }
    }

    /**
     * Gets screen y.
     *
     * @return the screen y
     */
    public double getScreenY() {
        return this.screenY;
    }

    /**
     * Gets screen x.
     *
     * @return the screen x
     */
    public double getScreenX() {
        return this.screenX;
    }

    /**
     * Is moving boolean.
     *
     * @return the boolean
     */
    public boolean isMoving() {
        return this.keyManager.isMoving();
    }

    /**
     * Is paused boolean.
     *
     * @return the boolean
     */
    public boolean isPaused() {
        return this.keyManager.isPaused();
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public int getCurrency() {
        return currency;
    }

    /**
     * Add currency.
     *
     * @param amount the amount
     */
    public void addCurrency(int amount) {
        currency += amount;
    }

    /**
     * Subtract currency.
     *
     * @param amount the amount
     */
    public static void subtractCurrency(int amount) {
        if (amount <= currency) {
            currency -= amount;
        }
    }

    /**
     * Gets mining speed.
     *
     * @return the mining speed
     */
    public double getMiningSpeed() {
        return this.miningSpeed;
    }

    /**
     * Sets mining speed.
     *
     * @param miningSpeed the mining speed
     */
    public void setMiningSpeed(double miningSpeed) {
        this.miningSpeed = miningSpeed;
    }

    /**
     * Adds an item to the players invetory
     *
     * @param item the item
     */
    public static void addInventoryItem(Item item) {
        inventory.addItem(item);
    }
}
