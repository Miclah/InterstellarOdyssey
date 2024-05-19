package game.things;

/**
 * Item a subclass of Thing, has special features
 */
public class Item extends Thing {
    /**
     * The Modifier.
     */
    private Modifier modifier;

    /**
     * Instantiates a new Item.
     *
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param imagePath   the image path
     */
    public Item(String name, String description, int price, String imagePath) {
        super (name, description, price, imagePath);
        this.modifier = null;
    }

    /**
     * Gets modifier.
     *
     * @return the modifier
     */
    public Modifier getModifier() {
        return this.modifier;
    }

    /**
     * Sets modifier.
     *
     * @param modifier the modifier
     */
    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }
}
