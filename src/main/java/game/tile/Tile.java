package game.tile;

import javafx.scene.image.Image;

/**
 * The Class Tile.
 */
public class Tile {
    /**
     * The Image.
     */
    private Image image;
    /**
     * The Colision.
     */
    private boolean colision = false;

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Sets colision.
     *
     * @param colision the colision
     */
    public void setColision(boolean colision) {
        this.colision = colision;
    }

    /**
     * Is collision boolean.
     *
     * @return the boolean
     */
    public boolean isCollision() {
        return this.colision;
    }
}
