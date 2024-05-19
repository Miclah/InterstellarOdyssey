package game.things;

public abstract class Thing {
    private final String name;
    private final String description;
    private int price;
    private String imagePath;

    public Thing(String name, String description, int price, String imagePath) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = Math.max(price, 0);
    }

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public String toString() {
        return this.name + " " + this.description + " " + this.price;
    }
}
