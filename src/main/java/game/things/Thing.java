package game.things;

public abstract class Thing {
    private final String name;
    private final String description;
    private int price;

    public Thing(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    @Override
    public String toString() {
        return this.name + " " + this.description + " " + this.price;
    }
}
