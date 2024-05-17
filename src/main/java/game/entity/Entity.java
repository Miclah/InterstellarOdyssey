package game.entity;

import game.io.Loader;
import game.state.GeneralManager;
import game.util.Direction;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public abstract class Entity implements Movable {

    private double worldX, worldY;
    private final String name;
    private Label label;
    private Rectangle collisionRectangle;
    private ArrayList<Image> frames;
    private ImageView currentFrame;
    private Direction direction;
    private boolean collision = false;
    private final int baseSpeed;
    private final GeneralManager manager;
    private double currentSpeed;
    private double spriteCounter;
    private int spriteNumber;

    public Entity(int worldX, int worldY, String name, String pathToImage, int baseSpeed, GeneralManager manager) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.name = name;
        this.baseSpeed = baseSpeed;
        this.currentSpeed = 0;
        this.frames = new ArrayList<>();
        this.frames = Loader.loadImages(pathToImage);
        this.currentFrame = new ImageView(this.frames.get(1));
        this.manager = manager;
        this.createLabel();
        this.collisionRectangle = new Rectangle(22, 32, 22, 32);
    }

    public void createLabel() {
        this.label = new Label(this.name);
        this.label.setTextFill(Color.WHITE);
        this.label.setStyle("-fx-background-color: transparent; -fx-padding: 5px;");
        Font customFont = Font.loadFont(this.getClass ().getResourceAsStream("/fonts/pixel1.ttf"), 20);
        this.label.setFont(customFont);
        this.label.setVisible(true);

    }

    public Label getLabel() {
        return this.label;
    }

    public void updateLabelPosition(double entityX, double entityY) {
        double labelWidth = this.label.getBoundsInLocal().getWidth();
        double labelHeight = this.label.getBoundsInLocal().getHeight();
        double imageWidth = this.currentFrame.getImage().getWidth();

        double entityCenterX = entityX + imageWidth / 2;
        double labelX = entityCenterX - labelWidth / 2;

        this.label.setLayoutX(labelX);
        this.label.setLayoutY(entityY - labelHeight);
    }

    @Override
    public void move() {
        if (this.direction != null) {
            this.currentSpeed = Math.min(this.currentSpeed + 0.1, this.baseSpeed);

            double moveX = 0;
            double moveY = 0;

            switch (this.direction) {
                case UP -> moveY = -this.currentSpeed;
                case DOWN -> moveY = this.currentSpeed;
                case LEFT -> moveX = -this.currentSpeed;
                case RIGHT -> moveX = this.currentSpeed;
                case UP_LEFT -> {
                    moveY = -this.currentSpeed / Math.sqrt(2);
                    moveX = -this.currentSpeed / Math.sqrt(2);
                }
                case UP_RIGHT -> {
                    moveY = -this.currentSpeed / Math.sqrt(2);
                    moveX = this.currentSpeed / Math.sqrt(2);
                }
                case DOWN_LEFT -> {
                    moveY = this.currentSpeed / Math.sqrt(2);
                    moveX = -this.currentSpeed / Math.sqrt(2);
                }
                case DOWN_RIGHT -> {
                    moveY = this.currentSpeed / Math.sqrt(2);
                    moveX = this.currentSpeed / Math.sqrt(2);
                }
            }

            this.collision = false;
            this.manager.getCollision().check(this);
            if (!this.collision) {
                this.worldX += moveX;
                this.worldY += moveY;
            }

            this.spriteCounter++;
            if (this.spriteCounter > 15) {
                this.spriteNumber = this.spriteNumber == 1 ? 2 : 1;
                this.spriteCounter = 0;
            }
            this.changeFrame();
        } else {
            this.currentSpeed = Math.max(this.currentSpeed - 0.2, 0);
        }
    }

    public void changeFrame() {
        if (this.direction == null) {
            return;
        }
        switch (this.direction) {
            case UP, UP_LEFT, UP_RIGHT -> {
                if (this.spriteNumber == 1) {
                    this.changeImage(this.frames.get(9));
                } else {
                    this.changeImage(this.frames.get(11));
                }
            }
            case DOWN, DOWN_LEFT, DOWN_RIGHT -> {
                if (this.spriteNumber == 1) {
                    this.changeImage(this.frames.getFirst());
                } else {
                    this.changeImage(this.frames.get(2));
                }
            }
            case LEFT -> {
                if (this.spriteNumber == 1) {
                    this.changeImage(this.frames.get(3));
                } else {
                    this.changeImage(this.frames.get(5));
                }
            }
            case RIGHT -> {
                if (this.spriteNumber == 1) {
                    this.changeImage(this.frames.get(6));
                } else {
                    this.changeImage(this.frames.get(8));
                }
            }
        }
    }

    public void setWorldX(double worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(double worldY) {
        this.worldY = worldY;
    }

    public double getWorldX() {
        return this.worldX;
    }

    public double getWorldY() {
        return this.worldY;
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

    public ImageView getCurrentFrame() {
        return this.currentFrame;
    }

    public void setCurrentFrame(ImageView currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void changeImage(Image imageToChangeTo) {
        this.currentFrame.setImage(imageToChangeTo);
    }

    public Image getCurrentFrameImage() {
        return this.currentFrame.getImage();
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isCollision() {
        return this.collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getBaseSpeed() {
        return this.baseSpeed;
    }

    public GeneralManager getManager() {
        return this.manager;
    }
}
