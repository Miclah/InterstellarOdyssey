package game.things;

public class Item extends Thing {
    private Modifier modifier;
    private String imagePath;

    public Item(String name, String description, int price, String imagePath) {
        super (name, description, price, imagePath);
        this.imagePath = imagePath;
        this.modifier = null;
    }

    public Modifier getModifier() {
        return this.modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }
}
