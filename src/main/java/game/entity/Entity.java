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

public abstract class Entity {

    private double worldX, worldY;
    private final String name;
    private Label label;
    private Rectangle collisionRectangle;
    private ArrayList<Image> frames;
    private ImageView currentFrame;
    private Direction direction;
    private boolean collision = false;
    private final int baseSpeed;

    public Entity(int worldX, int worldY, String name, String pathToImage, int baseSpeed, GeneralManager manager) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.name = name;
        this.baseSpeed = baseSpeed;
        this.frames = new ArrayList<>();
        this.frames = Loader.loadImages(pathToImage);
        this.currentFrame = new ImageView(this.frames.get(1));
        this.createLabel();
        this.collisionRectangle = new Rectangle(14, 28, 32, 32);
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
}
