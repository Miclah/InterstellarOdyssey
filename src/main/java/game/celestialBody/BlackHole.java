package game.celestialBody;

/**
 * The type Black hole.
 */
public class BlackHole extends CelestialBody {
    /**
     * The Gravity force.
     */
    private int gravityForce;
    /**
     * The Radiation.
     */
    private double radiation;

    /**
     * Instantiates a new Black hole.
     *
     * @param mapX           the map x
     * @param mapY           the map y
     * @param rotationSpeed  the rotation speed
     * @param mass           the mass
     * @param radius         the radius
     * @param pathToFrames   the path to frames
     * @param numberOfFrames the number of frames
     * @param description    the description
     * @param gravityForce   the gravity force
     */
    public BlackHole(int mapX, int mapY, double rotationSpeed, double mass, int radius, String pathToFrames, int numberOfFrames, String description, int gravityForce) {
        super (mapX, mapY, rotationSpeed, mass, radius, pathToFrames, numberOfFrames, description);
        this.gravityForce = gravityForce;
        this.radiation = 1.0;
    }
}
