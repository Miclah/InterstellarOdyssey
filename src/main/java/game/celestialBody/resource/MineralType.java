package game.celestialBody.resource;

/**
 * The enum Mineral type.
 */
public enum MineralType {
    /**
     * Aluminium mineral type.
     */
    ALUMINIUM(50),
    /**
     * Copper mineral type.
     */
    COPPER(70),
    /**
     * Diamond mineral type.
     */
    DIAMOND(300),
    /**
     * Emerald mineral type.
     */
    EMERALD(250),
    /**
     * Gold mineral type.
     */
    GOLD(200),
    /**
     * Iron mineral type.
     */
    IRON(40),
    /**
     * Quartz mineral type.
     */
    QUARTZ(30);

    /**
     * The Value.
     */
    private final int value;

    /**
     * Instantiates a new Mineral type.
     *
     * @param value the value
     */
    MineralType(int value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
}
