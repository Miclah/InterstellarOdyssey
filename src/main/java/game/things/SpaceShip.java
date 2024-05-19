package game.things;

import game.io.Loader;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * SpaceShip which is the superclass for all spaceShips
 */
public class SpaceShip {
    /**
     * The Speed.
     */
    private double speed;
    /**
     * The Inventory size.
     */
    private int inventorySize;
    /**
     * The Fuel capacity.
     */
    private int fuelCapacity;
    /**
     * The Cargo capacity.
     */
    private int cargoCapacity;
    /**
     * The Ship health.
     */
    private int shipHealth;
    /**
     * The Shield strength.
     */
    private double shieldStrength;
    /**
     * The Acceleration.
     */
    private double acceleration;
    /**
     * The Frames.
     */
    private ArrayList<Image> frames;

    /**
     * Instantiates a new Spaceship.
     *
     * @param speed         the speed
     * @param inventorySize the inventory size
     * @param fuelCapacity  the fuel capacity
     * @param cargoCapacity the cargo capacity
     * @param pathToFrames  the path to frames
     */
    public SpaceShip(double speed, int inventorySize, int fuelCapacity, int cargoCapacity, String pathToFrames) {
        this.speed = speed;
        this.inventorySize = inventorySize;
        this.fuelCapacity = fuelCapacity;
        this.cargoCapacity = cargoCapacity;
        this.shipHealth = 100;
        this.shieldStrength = 100.0;
        this.acceleration = 1.0;
        this.frames = Loader.loadImages(pathToFrames);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Gets inventory size.
     *
     * @return the inventory size
     */
    public int getInventorySize() {
        return this.inventorySize;
    }

    /**
     * Gets fuel capacity.
     *
     * @return the fuel capacity
     */
    public int getFuelCapacity() {
        return this.fuelCapacity;
    }

    /**
     * Gets cargo capacity.
     *
     * @return the cargo capacity
     */
    public int getCargoCapacity() {
        return this.cargoCapacity;
    }

    /**
     * Gets ship health.
     *
     * @return the ship health
     */
    public int getShipHealth() {
        return this.shipHealth;
    }

    /**
     * Gets shield strength.
     *
     * @return the shield strength
     */
    public double getShieldStrength() {
        return this.shieldStrength;
    }

    /**
     * Gets acceleration.
     *
     * @return the acceleration
     */
    public double getAcceleration() {
        return this.acceleration;
    }

    /**
     * Gets frames.
     *
     * @return the frames
     */
    public ArrayList<Image> getFrames() {
        return this.frames;
    }
}
