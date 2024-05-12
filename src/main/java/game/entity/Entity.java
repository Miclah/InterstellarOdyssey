package game.entity;

import game.util.SpriteLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Entity {

    private int worldX, worldY;
    private final String name;
    private Label label;
    private Rectangle collisionRectangle;
    private ArrayList<Image> frames;

    public Entity(int worldX, int worldY, String name, String pathToImage) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.name = name;
        this.frames = new ArrayList<>();
        this.frames = SpriteLoader.loadImages(pathToImage);
        this.createLabel();
    }

    public void createLabel() {
        this.label = new Label(this.name);
        this.label.setTextFill(Color.BLACK);
        this.label.setStyle("-fx-background-color: transparent; -fx-padding: 5px;");
        this.label.setVisible(true);
    }

    public Label getLabel() {
        return this.label;
    }

    public int getWorldX() {
        return this.worldX;
    }

    public int getWorldY() {
        return this.worldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    public ArrayList<Image> getFrames() {
        return this.frames;
    }

    public void setCollisionRectangle(Rectangle collisionRectangle) {
        this.collisionRectangle = collisionRectangle;
    }
}
