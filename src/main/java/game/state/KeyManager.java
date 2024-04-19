package game.state;

import game.util.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyManager {
    private boolean isMoving;
    private Direction lastDirection;
    private Direction currentDirection;

    public KeyManager() {
        this.isMoving = false;
        this.currentDirection = Direction.DOWN;
    }

    public Direction move(KeyEvent event) {
        if (event != null) {
            if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.S ||
                    event.getCode() == KeyCode.D || event.getCode() == KeyCode.W) {
                this.isMoving = true;
                switch (event.getCode()) {
                    case W:
                        this.currentDirection = Direction.UP;
                        this.lastDirection = Direction.UP;
                        break;
                    case S:
                        this.currentDirection = Direction.DOWN;
                        this.lastDirection = Direction.DOWN;
                        break;
                    case A:
                        this.currentDirection = Direction.LEFT;
                        this.lastDirection = Direction.LEFT;
                        break;
                    case D:
                        this.currentDirection = Direction.RIGHT;
                        this.lastDirection = Direction.RIGHT;
                        break;
                }
            } else {
                this.isMoving = false;
                this.currentDirection = null;
            }
        }
        return this.currentDirection;
    }

    public boolean isMoving() {
        return this.isMoving;
    }

    public Direction getLastDirection() {
        return this.lastDirection;
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }
}
