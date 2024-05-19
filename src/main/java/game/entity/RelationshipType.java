package game.entity;

/**
 * The enum Relationship type.
 */
public enum RelationshipType {
    HOSTILE(0),
    UNFRIENDLY(25),
    NEUTRAL(50),
    FRIENDLY(75),
    ALLY(100);

    private final int value;

    /**
     * Instantiates a new Relationship type.
     *
     * @param value the value
     */
    RelationshipType(int value) {
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
