package game.celestialBody;

import java.util.ArrayList;

/**
 * The type Star.
 */
public class Star extends CelestialBody {
    /**
     * The Bodies in system.
     */
    private ArrayList<CelestialBody> bodiesInSystem;
    /**
     * The Star type.
     */
    private StarType starType;

    /**
     * Instantiates a new Star.
     *
     * @param mapX           the map x
     * @param mapY           the map y
     * @param rotationSpeed  the rotation speed
     * @param mass           the mass
     * @param radius         the radius
     * @param pathToFrames   the path to frames
     * @param numberOfFrames the number of frames
     * @param description    the description
     * @param starType       the star type
     */
    public Star(int mapX, int mapY, double rotationSpeed, double mass, int radius, String pathToFrames, int numberOfFrames, String description, StarType starType) {
        super(mapX, mapY, rotationSpeed, mass, radius, pathToFrames, numberOfFrames, description);
        this.bodiesInSystem = new ArrayList<>();
        this.starType = starType;
    }
}
