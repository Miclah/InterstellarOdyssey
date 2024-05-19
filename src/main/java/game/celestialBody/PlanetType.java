package game.celestialBody;

/**
 * The enum Planet type.
 */
public enum PlanetType {
    /**
     * The Terrain wet.
     */
    TERRAIN_WET("Wet Terrain"),
    /**
     * The Terrain dry.
     */
    TERRAIN_DRY("Dry Terrain"),
    /**
     * Barren planet type.
     */
    BARREN("Barren"),
    /**
     * The Gas giant type 1.
     */
    GAS_GIANT_TYPE_1("Gas Giant Type 1"),
    /**
     * The Gas giant type 2.
     */
    GAS_GIANT_TYPE_2("Gas Giant Type 2"),
    /**
     * The Lava world.
     */
    LAVA_WORLD("Lava World"),
    /**
     * The Ice world.
     */
    ICE_WORLD("Ice World"),
    /**
     * Island planet type.
     */
    ISLAND("Island");

    /**
     * The Display name.
     */
    private final String displayName;

    /**
     * Instantiates a new Planet type.
     *
     * @param displayName the display name
     */
    PlanetType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return this.displayName;
    }
}
