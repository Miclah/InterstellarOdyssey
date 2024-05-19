package game.things;

import game.entity.Player;

/**
 * Modifier who modifies certain aspects of the game
 * such as players mining speed, planet mineral spawn rate...
 */
public abstract class Modifier implements Applicable {
    /**
     * The Name of the modifer
     */
    private final String name;
    /**
     * The Description.
     */
    private final String description;
    /**
     * Specifies whether the given modifier has an effect on the player
     */
    private final boolean isPlayerModifier;

    /**
     * Instantiates a new Modifier.
     *
     * @param name        the name
     * @param description the description
     */
    public Modifier(String name, String description, boolean isPlayerModifier) {
        this.name = name;
        this.description = description;
        this.isPlayerModifier = isPlayerModifier;
    }

    /**
     * Applys the modifiers effect to the player
     *
     * @param player the player
     */
    @Override
    public void applyEffect(Player player) {
    }

    abstract void applyEffect();

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Is player modifier boolean.
     *
     * @return the boolean
     */
    public boolean isPlayerModifier() {
        return this.isPlayerModifier;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return this.name + " " + this.description;
    }
}
