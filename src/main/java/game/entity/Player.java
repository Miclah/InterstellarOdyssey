package game.entity;

import game.util.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Player extends Entity {
    private final ArrayList<Image> frames;
    private ImageView currentFrame;
    private Direction direction;
    private int spriteNumber;
    private int spriteCounter;
    private int x;
    private int y;
    private int speed;
    private boolean isMoving;
    private Direction lastDirection;

    public Player(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.frames = this.loadImages();
        this.setDefaultValues();
        this.currentFrame = new ImageView(this.frames.get(2));
    }

    @Override
    ArrayList<Image> loadImages() {
        ArrayList<Image> frames = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String filename = String.format("/textures/player/player_%02d.png", i);
            Image image = new Image(filename);
            frames.add(image);
        }
        return frames;
    }

    public void setDefaultValues() {
        this.x = 0;
        this.y = 0;
        this.speed = 20;
        this.direction = Direction.DOWN;
        this.spriteNumber = 1;
        this.isMoving = false;
    }

    public void update(KeyEvent event) {
        if (event != null) {
            if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.S ||
                    event.getCode() == KeyCode.D || event.getCode() == KeyCode.W) {
                this.isMoving = true;
                switch (event.getCode()) {
                    case W:
                        this.y -= this.speed;
                        this.direction = Direction.UP;
                        this.lastDirection = Direction.UP;
                        break;
                    case S:
                        this.y += this.speed;
                        this.direction = Direction.DOWN;
                        this.lastDirection = Direction.DOWN;
                        break;
                    case A:
                        this.x -= this.speed;
                        this.direction = Direction.LEFT;
                        this.lastDirection = Direction.LEFT;
                        break;
                    case D:
                        this.x += this.speed;
                        this.direction = Direction.RIGHT;
                        this.lastDirection = Direction.RIGHT;
                        break;
                }
                this.spriteCounter++;
                if (this.spriteCounter > 0) {
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
        if (!this.isMoving) {
            switch (this.lastDirection) {
                case UP:
                    this.currentFrame.setImage(this.frames.get(0));
                    break;
                case DOWN:
                    this.currentFrame.setImage(this.frames.get(2));
                    break;
                case LEFT:
                    this.currentFrame.setImage(this.frames.get(5));
                    break;
                case RIGHT:
                    this.currentFrame.setImage(this.frames.get(8));
                    break;
            }
        } else {
            switch (this.direction) {
                case UP:
                    if (this.spriteNumber == 1) {
                        this.currentFrame.setImage(this.frames.get(1));
                    } else {
                        this.currentFrame.setImage(this.frames.get(11));
                    }
                    break;
                case DOWN:
                    if (this.spriteNumber == 1) {
                        this.currentFrame.setImage(this.frames.get(3));
                    } else {
                        this.currentFrame.setImage(this.frames.get(4));
                    }
                    break;
                case LEFT:
                    if (this.spriteNumber == 1) {
                        this.currentFrame.setImage(this.frames.get(6));
                    } else {
                        this.currentFrame.setImage(this.frames.get(7));
                    }
                    break;
                case RIGHT:
                    if (this.spriteNumber == 1) {
                        this.currentFrame.setImage(this.frames.get(9));
                    } else {
                        this.currentFrame.setImage(this.frames.get(10));
                    }
                    break;
            }
        }
    }

    public ImageView getImageView() {
        return this.currentFrame;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
