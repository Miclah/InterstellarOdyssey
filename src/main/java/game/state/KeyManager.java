package game.state;

import game.util.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyManager {
    private Direction currentDirection;
    private boolean paused;
    private boolean isMoving;
    private boolean wDown;
    private boolean aDown;
    private boolean sDown;
    private boolean dDown;

    public KeyManager() {
        this.currentDirection = Direction.DOWN;
        this.paused = false;
        this.isMoving = false;
        this.wDown = false;
        this.aDown = false;
        this.sDown = false;
        this.dDown = false;
    }

    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.P) {
            this.paused = !this.paused;
            return;
        }

        if (!this.paused) {
            switch (event.getCode()) {
                case W -> this.wDown = true;
                case A -> this.aDown = true;
                case S -> this.sDown = true;
                case D -> this.dDown = true;
            }
            this.updateDirection();
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        if (!this.paused) {
            switch (event.getCode()) {
                case W -> this.wDown = false;
                case A -> this.aDown = false;
                case S -> this.sDown = false;
                case D -> this.dDown = false;
            }
            this.updateDirection();
        }
    }

    private void updateDirection() {
        this.isMoving = this.wDown || this.aDown || this.sDown || this.dDown;

        if (this.isMoving) {
            if (this.wDown && this.aDown) {
                this.currentDirection = Direction.UP_LEFT;
            } else if (this.wDown && this.dDown) {
                this.currentDirection = Direction.UP_RIGHT;
            } else if (this.sDown && this.aDown) {
                this.currentDirection = Direction.DOWN_LEFT;
            } else if (this.sDown && this.dDown) {
                this.currentDirection = Direction.DOWN_RIGHT;
            } else if (this.wDown) {
                this.currentDirection = Direction.UP;
            } else if (this.sDown) {
                this.currentDirection = Direction.DOWN;
            } else if (this.aDown) {
                this.currentDirection = Direction.LEFT;
            } else if (this.dDown) {
                this.currentDirection = Direction.RIGHT;
            }
        } else {
            this.currentDirection = null;
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
