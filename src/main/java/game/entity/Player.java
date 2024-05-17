package game.entity;

import game.state.GeneralManager;
import game.state.KeyManager;
import game.util.Direction;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Player extends Entity {

    private int spriteNumber;
    private int spriteCounter;
    private double currentSpeed;
    private final double screenX;
    private final double screenY;
    private final KeyManager keyManager;
    private GeneralManager manager;

    public Player(int worldX, int worldY, int speed, String name, Scene scene, KeyManager keyManager, GeneralManager manager) {
        super(worldX, worldY, name, "player/skin/player", speed, manager);
        this.currentSpeed = 0;
        this.screenX = scene.getWidth() / 2 - 32;
        this.screenY = scene.getHeight() / 2 - 32;
        this.keyManager = keyManager;
        this.manager = manager;
        scene.setOnKeyPressed(this.keyManager::handleKeyPressed);
        scene.setOnKeyReleased(this.keyManager::handleKeyReleased);
    }

    public void update() {
        if (this.keyManager.isMoving() && !this.keyManager.isPaused()) {
            super.setDirection(this.keyManager.getCurrentDirection());
            if (super.getDirection() != null) {
                this.currentSpeed = Math.min(this.currentSpeed + 0.1, super.getBaseSpeed());

                double moveX = 0;
                double moveY = 0;

                switch (super.getDirection()) {
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

                super.setCollision(false);
                this.manager.getCollision().check(this);
                System.out.println (super.isCollision());
                if (!super.isCollision()) {
                    super.setWorldX(super.getWorldX() + moveX);
                    super.setWorldY(super.getWorldY() + moveY);
                }

                this.spriteCounter++;
                if (this.spriteCounter > 15) {
                    this.spriteNumber = this.spriteNumber == 1 ? 2 : 1;
                    this.spriteCounter = 0;
                }
                this.changeFrame();
            }
        } else {
            this.currentSpeed = Math.max(this.currentSpeed - 0.2, 0);
        }
    }

    public void changeFrame() {
        if (super.getDirection() == null) {
            return;
        }
        switch (super.getDirection()) {
            case UP, UP_LEFT, UP_RIGHT -> {
                if (this.spriteNumber == 1) {
                    super.changeImage(super.getFrames().get(9));
                } else {
                    super.changeImage(super.getFrames().get(11));
                }
            }
            case DOWN, DOWN_LEFT, DOWN_RIGHT -> {
                if (this.spriteNumber == 1) {
                    super.changeImage(super.getFrames().getFirst());
                } else {
                    super.changeImage(super.getFrames().get(2));
                }
            }
            case LEFT -> {
                if (this.spriteNumber == 1) {
                    super.changeImage(super.getFrames().get(3));
                } else {
                    super.changeImage(super.getFrames().get(5));
                }
            }
            case RIGHT -> {
                if (this.spriteNumber == 1) {
                    super.changeImage(super.getFrames().get(6));
                } else {
                    super.changeImage(super.getFrames().get(8));
                }
            }
        }
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
