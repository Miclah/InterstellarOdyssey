package game.tile;

import javafx.scene.image.Image;

public class Tile {
    private Image image;
    private boolean colision = false;

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }
    public boolean isCollision() {
        return this.colision;
    }
}
