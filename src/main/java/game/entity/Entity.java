package game.entity;

import game.entity.interfazy.Movable;
import game.io.Loader;
import game.state.GeneralManager;
import game.util.Direction;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Entity superclass which is the parent of
 * all living beings in the game
 */
public abstract class Entity implements Movable {

    /**
     * The World x.
     */
    private double worldX;
    /**
     * The World y.
     */
    private double worldY;
    /**
     * The Name.
     */
    private final String name;
    /**
     * The Label(Nametag).
     */
    private Label label;
    /**
     * The Collision rectangle.
     */
    private Rectangle collisionRectangle;
    /**
     * The Frames(Images).
     */
    private ArrayList<Image> frames;
    /**
     * The Current frame.
     */
    private ImageView currentFrame;
    /**
     * The Direction.
     */
    private Direction direction;
    /**
     * The Collision.
     */
    private boolean collision;
    /**
     * The Base speed.
     */
    private final int baseSpeed;
    /**
     * The Manager.
     */
    private final GeneralManager manager;
    /**
     * The Current speed.
     */
    private double currentSpeed;
    /**
     * The Sprite counter.
     */
    private double spriteCounter;
    /**
     * The Sprite number.
     */
    private int spriteNumber;

    /**
     * Instantiates a new Entity.
     *
     * @param worldX      the world x
     * @param worldY      the world y
     * @param name        the name
     * @param pathToImage the path to image
     * @param baseSpeed   the base speed
     * @param manager     the manager
     */
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

    /**
     * Creates a nameTag that will be displayed above player
     */
    public void createLabel() {
        this.label = new Label(this.name);
        this.label.setTextFill(Color.WHITE);
        this.label.setStyle("-fx-background-color: transparent; -fx-padding: 5px;");
        Font customFont = Font.loadFont(this.getClass ().getResourceAsStream("/fonts/pixel1.ttf"), 20);
        this.label.setFont(customFont);
        this.label.setVisible(true);

    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public Label getLabel() {
        return this.label;
    }

    /**
     * Updates labels position so that it always stays above npc
     *
     * @param entityX the entity x
     * @param entityY the entity y
     */
    public void updateLabelPosition(double entityX, double entityY) {
        double labelWidth = this.label.getBoundsInLocal().getWidth();
        double labelHeight = this.label.getBoundsInLocal().getHeight();
        double imageWidth = this.currentFrame.getImage().getWidth();

        double entityCenterX = entityX + imageWidth / 2;
        double labelX = entityCenterX - labelWidth / 2;

        this.label.setLayoutX(labelX);
        this.label.setLayoutY(entityY - labelHeight);
    }

    /**
     * Makes an entity move to a specified direction,
     * also responsible for changing frames creating a
     * walking animation and checking collisions.
     */
    @Override
    public void move() {
        if (this.direction != null) {
            this.currentSpeed = Math.min(this.currentSpeed + 0.1, this.baseSpeed);

            double moveX = 0;
            double moveY = 0;

            switch (this.direction) {
                case UP -> {
                    moveY = -this.currentSpeed;
                }
                case DOWN -> {
                    moveY = this.currentSpeed;
                }
                case LEFT -> {
                    moveX = -this.currentSpeed;
                }
                case RIGHT -> {
                    moveX = this.currentSpeed;
                }
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

            this.worldX += moveX;
            this.worldY += moveY;
            this.collision = false;
            this.manager.getCollision().check(this);

            if (!this.collision) {
                this.worldX += moveX;
                this.worldY += moveY;
            } else {
                this.worldX -= moveX;
                this.worldY -= moveY;
                this.setDirection(this.direction.opposite());
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


    /**
     * Changes frames(Images) to create a walking animation
     */
    public void changeFrame() {
        if (this.direction == null) {
            return;
        }
        switch (this.direction) {
            case UP -> {
                if (this.spriteNumber == 1) {
                    this.changeImage(this.frames.get(9));
                } else {
                    this.changeImage(this.frames.get(11));
                }
            }
            case DOWN -> {
                if (this.spriteNumber == 1) {
                    this.changeImage(this.frames.getFirst());
                } else {
                    this.changeImage(this.frames.get(2));
                }
            }
            case LEFT, UP_LEFT, DOWN_LEFT -> {
                if (this.spriteNumber == 1) {
                    this.changeImage(this.frames.get(3));
                } else {
                    this.changeImage(this.frames.get(5));
                }
            }
            case RIGHT, DOWN_RIGHT, UP_RIGHT -> {
                if (this.spriteNumber == 1) {
                    this.changeImage(this.frames.get(6));
                } else {
                    this.changeImage(this.frames.get(8));
                }
            }
        }
    }

    /**
     * Sets world x.
     *
     * @param worldX the world x
     */
    public void setWorldX(double worldX) {
        this.worldX = worldX;
    }

    /**
     * Sets world y.
     *
     * @param worldY the world y
     */
    public void setWorldY(double worldY) {
        this.worldY = worldY;
    }

    /**
     * Gets world x.
     *
     * @return the world x
     */
    public double getWorldX() {
        return this.worldX;
    }

    /**
     * Gets world y.
     *
     * @return the world y
     */
    public double getWorldY() {
        return this.worldY;
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * Gets current frame.
     *
     * @return the current frame
     */
    public ImageView getCurrentFrame() {
        return this.currentFrame;
    }

    /**
     * Gets name of the entity.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Change image.
     *
     * @param imageToChangeTo the image to change to
     */
    public void changeImage(Image imageToChangeTo) {
        this.currentFrame.setImage(imageToChangeTo);
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Is collision boolean.
     *
     * @return the boolean
     */
    public boolean isCollision() {
        return this.collision;
    }

    /**
     * Sets collision.
     *
     * @param collision the collision
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    /**
     * Gets base speed.
     *
     * @return the base speed
     */
    public int getBaseSpeed() {
        return this.baseSpeed;
    }

    /**
     * Gets manager.
     *
     * @return the manager
     */
    public GeneralManager getManager() {
        return this.manager;
    }

    /**
     * Gets current speed.
     *
     * @return the current speed
     */
    public double getCurrentSpeed() {
        return this.currentSpeed;
    }

    /**
     * Gets sprite counter.
     *
     * @return the sprite counter
     */
    public double getSpriteCounter() {
        return this.spriteCounter;
    }

    /**
     * Gets sprite number.
     *
     * @return the sprite number
     */
    public int getSpriteNumber() {
        return this.spriteNumber;
    }

    /**
     * Sets current speed.
     *
     * @param currentSpeed the current speed
     */
    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    /**
     * Sets sprite counter.
     *
     * @param spriteCounter the sprite counter
     */
    public void setSpriteCounter(double spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    /**
     * Sets sprite number.
     *
     * @param spriteNumber the sprite number
     */
    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }
}
