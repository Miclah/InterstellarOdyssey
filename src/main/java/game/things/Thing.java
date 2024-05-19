package game.things;

/**
 * Thing class, a superclass for every Thing(Item, Ship..) in the game
 */
public abstract class Thing {
    /**
     * The Name.
     */
    private final String name;
    /**
     * The Description.
     */
    private final String description;
    /**
     * The Price.
     */
    private int price;
    /**
     * The Image path.
     */
    private String imagePath;

    /**
     * Instantiates a new Thing.
     *
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param imagePath   the image path
     */
    public Thing(String name, String description, int price, String imagePath) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
    }

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
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = Math.max(price, 0);
    }

    /**
     * Gets image path.
     *
     * @return the image path
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return this.name + " " + this.description + " " + this.price;
    }
}
