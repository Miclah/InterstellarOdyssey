package game.things;

import game.entity.Player;

public abstract class Modifier {
    private final String name;
    private final String description;

    public Modifier(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void apply(Player player);

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.name + " " + this.description;
    }
}
