package game.entity;

public enum RelationshipType {
    HOSTILE(0),
    UNFRIENDLY(25),
    NEUTRAL(50),
    FRIENDLY(75),
    ALLY(100);

    private final int value;

    RelationshipType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
