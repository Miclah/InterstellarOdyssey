package game.celestialBody;

import game.io.Loader;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The type Celestial body.
 */
public class CelestialBody implements Rotatable {
    /**
     * The Rotation speed.
     */
    private double rotationSpeed;
    /**
     * The Mass.
     */
    private double mass;
    /**
     * The Map x.
     */
    private int mapX;
    /**
     * The Map y.
     */
    private int mapY;
    /**
     * The Radius.
     */
    private int radius;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Frames.
     */
    private ArrayList<Image> frames;

    /**
     * Instantiates a new Celestial body.
     *
     * @param mapX           the map x
     * @param mapY           the map y
     * @param rotationSpeed  the rotation speed
     * @param mass           the mass
     * @param radius         the radius
     * @param pathToFrames   the path to frames
     * @param numberOfFrames the number of frames
     * @param description    the description
     */
    public CelestialBody(int mapX, int mapY, double rotationSpeed, double mass, int radius, String pathToFrames, int numberOfFrames, String description) {
        this.mapX = mapX;
        this.mapY = mapY;
        this.mass = mass;
        this.rotationSpeed = rotationSpeed;
        this.radius = radius;
        this.description = description;
        this.frames = Loader.loadImages(pathToFrames, numberOfFrames);
    }

    /**
     * Rorate.
     */
    @Override
    public void rorate() {

    }
}
