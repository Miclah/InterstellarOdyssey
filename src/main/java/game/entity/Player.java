package game.entity;

import game.state.GeneralManager;
import game.state.KeyManager;
import javafx.scene.Scene;

public class Player extends Entity {

    private final double screenX;
    private final double screenY;
    private final KeyManager keyManager;

    public Player(int worldX, int worldY, int speed, String name, Scene scene, KeyManager keyManager, GeneralManager manager) {
        super(worldX, worldY, name, "player/skin/player", speed, manager);
        this.screenX = scene.getWidth() / 2 - 32;
        this.screenY = scene.getHeight() / 2 - 32;
        this.keyManager = keyManager;
        scene.setOnKeyPressed(this.keyManager::handleKeyPressed);
        scene.setOnKeyReleased(this.keyManager::handleKeyReleased);
    }

    public void update() {
        if (this.keyManager.isMoving() && !this.keyManager.isPaused()) {
            super.setDirection(this.keyManager.getCurrentDirection());
            super.move();
            super.getManager().getCollision().check(this);
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
