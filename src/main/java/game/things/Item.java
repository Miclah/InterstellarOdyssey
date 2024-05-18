package game.things;

public class Item extends Thing {
    private Modifier modifier;

    public Item(String name, String description, int price) {
        super (name, description, price);
        this.modifier = null;
    }

    public Modifier getModifier() {
        return this.modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }
}
