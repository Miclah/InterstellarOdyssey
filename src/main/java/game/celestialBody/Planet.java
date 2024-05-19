package game.celestialBody;

import game.celestialBody.resource.MineralType;
import game.celestialBody.resource.SpawnRate;

import java.util.HashMap;

/**
 * The type Planet.
 */
public class Planet extends CelestialBody {
    /**
     * The Resource map.
     */
    private HashMap<MineralType, Integer> resourceMap;
    /**
     * The Planet type.
     */
    private final PlanetType planetType;
    /**
     * The Spawn rate.
     */
    private final SpawnRate spawnRate;
    /**
     * The Overall value.
     */
    private int overallValue;

    /**
     * Instantiates a new Planet.
     *
     * @param mapX           the map x
     * @param mapY           the map y
     * @param rotationSpeed  the rotation speed
     * @param mass           the mass
     * @param radius         the radius
     * @param pathToFrames   the path to frames
     * @param numberOfFrames the number of frames
     * @param description    the description
     * @param planetType     the planet type
     * @param spawnRate      the spawn rate
     */
    public Planet(int mapX, int mapY, double rotationSpeed, double mass, int radius, String pathToFrames, int numberOfFrames, String description, PlanetType planetType, SpawnRate spawnRate) {
        super (mapX, mapY, rotationSpeed, mass, radius, pathToFrames, numberOfFrames, description);
        this.planetType = planetType;
        this.spawnRate = spawnRate;
        this.resourceMap = new HashMap<>();
        this.initializeResources();
        this.calculateOverallValue();
    }

    /**
     * Initialize resources.
     */
    private void initializeResources() {
        if (this.spawnRate.getRate() != 0) {
            for (MineralType mineralType : MineralType.values()) {
                int quantity = mineralType.getValue() * this.spawnRate.getRate();
                this.resourceMap.put(mineralType, quantity);
            }
        }
    }

    /**
     * Calculate overall value.
     */
    public void calculateOverallValue() {
        if (this.spawnRate.getRate() == 0) {
            this.overallValue = 0;
        } else {
            for (MineralType mineralType : this.resourceMap.keySet()) {
                this.overallValue += this.resourceMap.get(mineralType) * mineralType.getValue();
            }
        }
    }
}
