package game.celestialBody.resource;

/**
 * The enum Spawn rate.
 */
public enum SpawnRate {
    /**
     * None spawn rate.
     */
    NONE(0),
    /**
     * Low spawn rate.
     */
    LOW(1),
    /**
     * Medium spawn rate.
     */
    MEDIUM(2),
    /**
     * High spawn rate.
     */
    HIGH(3);

    /**
     * The Rate.
     */
    private final int rate;

    /**
     * Instantiates a new Spawn rate.
     *
     * @param rate the rate
     */
    SpawnRate(int rate) {
        this.rate = rate;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public int getRate() {
        return this.rate;
    }
}
