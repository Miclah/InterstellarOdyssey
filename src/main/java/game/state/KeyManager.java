package game.state;

import game.util.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyManager {
    private Direction currentDirection;
    private boolean paused;
    private boolean isMoving;

    public KeyManager() {
        this.currentDirection = Direction.DOWN;
        this.paused = false;
        this.isMoving = false;
    }

    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.P) {
            this.paused = !this.paused;
            return;
        }
        if (!this.paused) {
            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.A
                    || event.getCode() == KeyCode.S || event.getCode() == KeyCode.D) {
                this.isMoving = true;
                switch (event.getCode()) {
                    case W:
                        this.currentDirection = Direction.UP;
                        break;
                    case S:
                        this.currentDirection = Direction.DOWN;
                        break;
                    case A:
                        this.currentDirection = Direction.LEFT;
                        break;
                    case D:
                        this.currentDirection = Direction.RIGHT;
                        break;
                }
            }
        }
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public boolean isMoving() {
        return this.isMoving;
    }
}
