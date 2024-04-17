package game.entity;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Entity {

    private int worldX, worldY;
    private String name;
    private Label label;
    public abstract ArrayList<Image> loadImages();
    public abstract void createLabel();

    public Entity(int worldX, int worldY, String name) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.name = name;
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

    public void showLabel() {
        this.label.setVisible(true);
    }

    public void hideLabel() {
        this.label.setVisible(false);
    }

    public String getName() {
        return this.name;
    }

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
