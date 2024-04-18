package game.entity;

import game.util.Direction;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player extends Entity {
    // TODO: nemozes mat pristup k attributom predka musis ist settery
    private final ArrayList<Image> frames;
    private ImageView currentFrame;
    private Direction direction;
    private int spriteNumber;
    private int spriteCounter;
    private int speed;
    private boolean isMoving;
    private Direction lastDirection;
    private double screenX, screenY;
    private Scene scene;

    public Player(int worldX, int worldY, int speed, String name, Scene scene) {
        super(worldX, worldY, name);
        this.speed = speed;
        this.frames = this.loadImages();
        this.currentFrame = new ImageView(this.frames.get(1));
        this.lastDirection = Direction.DOWN;
        this.scene = scene;
        this.screenX = scene.getWidth() / 2 - 32;
        this.screenY = scene.getHeight() / 2 - 32;
        this.getLabel().layoutXProperty().bind(scene.widthProperty().divide(2).subtract(19));
        this.getLabel().layoutYProperty().bind(scene.heightProperty().divide(2).subtract(56));
    }

    @Override
    public ArrayList<Image> loadImages() {
        ArrayList<Image> frames = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String filename = String.format("/textures/player/skin/player%02d.png", i);
            Image image = new Image(filename);
            frames.add(image);
        }
        return frames;
    }

    public void update(KeyEvent event) {
        if (event != null) {
            if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.S ||
                    event.getCode() == KeyCode.D || event.getCode() == KeyCode.W) {
                this.isMoving = true;
                switch (event.getCode()) {
                    case W:
                        this.setWorldY(this.getWorldY() -this.speed);
                        this.direction = Direction.UP;
                        this.lastDirection = Direction.UP;
                        break;
                    case S:
                        this.setWorldY(this.getWorldY() + this.speed);
                        this.direction = Direction.DOWN;
                        this.lastDirection = Direction.DOWN;
                        break;
                    case A:
                        this.setWorldX(this.getWorldX() - this.speed);
                        this.direction = Direction.LEFT;
                        this.lastDirection = Direction.LEFT;
                        break;
                    case D:
                        this.setWorldX(this.getWorldX() + this.speed);
                        this.direction = Direction.RIGHT;
                        this.lastDirection = Direction.RIGHT;
                        break;
                }
                this.spriteCounter++;
                if (this.spriteCounter > 10) {
                    if (this.spriteNumber == 1) {
                        this.spriteNumber = 2;
                    } else {
                        this.spriteNumber = 1;
                    }
                    this.spriteCounter = 0;
                }
            } else {
                this.isMoving = false;
            }
            this.changeFrame();
        }
    }

    public void changeFrame() {
        // TODO: Default stance
        if (!this.isMoving) {
            switch (this.lastDirection) {
                case UP:
                    this.currentFrame.setImage(this.frames.get(10));
                    break;
                case DOWN:
                    this.currentFrame.setImage(this.frames.get(1));
                    break;
                case LEFT:
                    this.currentFrame.setImage(this.frames.get(4));
                    break;
                case RIGHT:
                    this.currentFrame.setImage(this.frames.get(7));
                    break;
            }
        } else {
            switch (this.direction) {
                case UP:
                    if (this.spriteNumber == 1) {
                        this.currentFrame.setImage(this.frames.get(9));
                    } else {
                        this.currentFrame.setImage(this.frames.get(11));
                    }
                    break;
                case DOWN:
                    if (this.spriteNumber == 1) {
                        this.currentFrame.setImage(this.frames.get(0));
                    } else {
                        this.currentFrame.setImage(this.frames.get(2));
                    }
                    break;
                case LEFT:
                    if (this.spriteNumber == 1) {
                        this.currentFrame.setImage(this.frames.get(3));
                    } else {
                        this.currentFrame.setImage(this.frames.get(5));
                    }
                    break;
                case RIGHT:
                    if (this.spriteNumber == 1) {
                        this.currentFrame.setImage(this.frames.get(6));
                    } else {
                        this.currentFrame.setImage(this.frames.get(8));
                    }
                    break;
            }
        }
    }

    public ImageView getImageView() {
        return this.currentFrame;
    }

    public double getScreenY() {
        return this.screenY;
    }

    public double getScreenX() {
        return this.screenX;
    }

    public boolean isMoving() {
        return this.isMoving;
    }
}
