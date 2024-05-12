package game.entity;

import game.state.KeyManager;
import game.util.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

// TODO: Colizie s objektami
public class Player extends Entity {

    private final ImageView currentFrame;
    private Direction direction;
    private int spriteNumber;
    private int spriteCounter;
    private final int speed;
    private final double screenX;
    private final double screenY;
    private final KeyManager keyManager;

    public Player(int worldX, int worldY, int speed, String name, Scene scene, KeyManager keyManager) {
        super(worldX, worldY, name, "player/skin/player");
        this.speed = speed;
        this.currentFrame = new ImageView(this.getFrames().get(1));
        this.screenX = scene.getWidth() / 2 - 32;
        this.screenY = scene.getHeight() / 2 - 32;
        this.getLabel().layoutXProperty().bind(scene.widthProperty().divide(2).subtract(19));
        this.getLabel().layoutYProperty().bind(scene.heightProperty().divide(2).subtract(56));
        this.setCollisionRectangle(new Rectangle(14, 28, 32, 32));
        this.keyManager = keyManager;
    }

    public void update(KeyEvent event) {
        if (event != null) {
            this.keyManager.handleKeyPressed(event);
            if (this.keyManager.isMoving() && !this.keyManager.isPaused()) {
                this.direction = this.keyManager.getCurrentDirection();
                if (this.direction != null) {
                    switch (this.direction) {
                        case UP -> this.setWorldY(this.getWorldY() - this.speed);
                        case DOWN -> this.setWorldY(this.getWorldY() + this.speed);
                        case LEFT -> this.setWorldX(this.getWorldX() - this.speed);
                        case RIGHT -> this.setWorldX(this.getWorldX() + this.speed);
                    }
                    this.spriteCounter++;
                    if (this.spriteCounter > 2) {
                        if (this.spriteNumber == 1) {
                            this.spriteNumber = 2;
                        } else {
                            this.spriteNumber = 1;
                        }
                        this.spriteCounter = 0;
                    }
                    this.changeFrame();
                }
            }
        }
    }

    public void changeFrame() {
        switch (this.direction) {
            case UP:
                if (this.spriteNumber == 1) {
                    this.currentFrame.setImage(this.getFrames().get(9));
                } else {
                    this.currentFrame.setImage(this.getFrames().get(11));
                }
                break;
            case DOWN:
                if (this.spriteNumber == 1) {
                    this.currentFrame.setImage(this.getFrames().get(0));
                } else {
                    this.currentFrame.setImage(this.getFrames().get(2));
                }
                break;
            case LEFT:
                if (this.spriteNumber == 1) {
                    this.currentFrame.setImage(this.getFrames().get(3));
                } else {
                    this.currentFrame.setImage(this.getFrames().get(5));
                }
                break;
            case RIGHT:
                if (this.spriteNumber == 1) {
                    this.currentFrame.setImage(this.getFrames().get(6));
                } else {
                    this.currentFrame.setImage(this.getFrames().get(8));
                }
                break;
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
        return this.keyManager.isMoving();
    }

    public boolean isPaused() {
        return this.keyManager.isPaused();
    }
}
