package game.entity;

import game.state.GeneralManager;
import game.state.KeyManager;
import javafx.scene.Scene;

public class Player extends Entity {

    private final double screenX;
    private final double screenY;
    private final KeyManager keyManager;
    private int currency;
    private double miningSpeed;

    public Player(int worldX, int worldY, int speed, String name, Scene scene, KeyManager keyManager, GeneralManager manager) {
        super(worldX, worldY, name, "player/skin/player", speed, manager);
        this.screenX = scene.getWidth() / 2 - 32;
        this.screenY = scene.getHeight() / 2 - 32;
        this.keyManager = keyManager;
        this.currency = 0;
        this.miningSpeed = 1.0;
        scene.setOnKeyPressed(this.keyManager::handleKeyPressed);
        scene.setOnKeyReleased(this.keyManager::handleKeyReleased);
    }

    public void update() {
        if (this.keyManager.isMoving() && !this.keyManager.isPaused()) {
            super.setDirection(this.keyManager.getCurrentDirection());
            super.getManager().getCollision().check(this);
            this.move();
        }
    }

    @Override
    public void move() {
        if (super.getDirection() != null) {
            super.setCurrentSpeed(Math.min(super.getCurrentSpeed() + 0.1, super.getBaseSpeed()));

            double moveX = 0;
            double moveY = 0;

            switch (super.getDirection()) {
                case UP -> {
                    moveY = -super.getCurrentSpeed();
                }
                case DOWN -> {
                    moveY = super.getCurrentSpeed();
                }
                case LEFT -> {
                    moveX = -super.getCurrentSpeed();
                }
                case RIGHT -> {
                    moveX = super.getCurrentSpeed();
                }
                case UP_LEFT -> {
                    moveY = -super.getCurrentSpeed() / Math.sqrt(2);
                    moveX = -super.getCurrentSpeed() / Math.sqrt(2);
                }
                case UP_RIGHT -> {
                    moveY = -super.getCurrentSpeed() / Math.sqrt(2);
                    moveX = super.getCurrentSpeed() / Math.sqrt(2);
                }
                case DOWN_LEFT -> {
                    moveY = super.getCurrentSpeed() / Math.sqrt(2);
                    moveX = -super.getCurrentSpeed() / Math.sqrt(2);
                }
                case DOWN_RIGHT -> {
                    moveY = super.getCurrentSpeed() / Math.sqrt(2);
                    moveX = super.getCurrentSpeed() / Math.sqrt(2);
                }
            }

            super.setCollision(false);
            super.getManager().getCollision().check(this);
            if (!super.isCollision()) {
                super.setWorldX(super.getWorldX() + moveX);
                super.setWorldY(super.getWorldY() + moveY);
            }

            super.setSpriteCounter(super.getSpriteCounter() + 1);
            if (super.getSpriteCounter() > 15) {
                if (super.getSpriteNumber() == 1) {
                    super.setSpriteNumber(2);
                } else {
                    super.setSpriteNumber(1);
                }
                super.setSpriteCounter(0);
            }
            this.changeFrame();
        } else {
            super.setCurrentSpeed(Math.max(super.getCurrentSpeed() - 0.2, 0));
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

    public int getCurrency() {
        return this.currency;
    }

    public void addCurrency(int amount) {
        this.currency += amount;
    }

    public void subtractCurrency(int amount) {
        if (amount <= this.currency) {
            this.currency -= amount;
        }
    }

    public double getMiningSpeed() {
        return this.miningSpeed;
    }

    public void setMiningSpeed(double miningSpeed) {
        this.miningSpeed = miningSpeed;
    }
}
