package game.state;

import game.gui.EarthSurface;
import game.util.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * KeyManager class responsible for managing player keyboard input
 */
public class KeyManager {
    /**
     * The Current direction.
     */
    private Direction currentDirection;
    /**
     * The Paused.
     */
    private boolean paused;
    /**
     * The Interacted.
     */
    private boolean interacted;
    /**
     * The Is moving.
     */
    private boolean isMoving;
    /**
     * The W down.
     */
    private boolean wDown;
    /**
     * The A down.
     */
    private boolean aDown;
    /**
     * The S down.
     */
    private boolean sDown;
    /**
     * The D down.
     */
    private boolean dDown;
    /**
     * The Shop open.
     */
    private boolean shopOpen;
    /**
     * The Earth surface.
     */
    private final EarthSurface earthSurface;

    /**
     * Instantiates a new Key manager.
     *
     * @param surface the surface
     */
    public KeyManager(EarthSurface surface) {
        this.currentDirection = Direction.DOWN;
        this.paused = false;
        this.interacted = false;
        this.isMoving = false;
        this.wDown = false;
        this.aDown = false;
        this.sDown = false;
        this.dDown = false;
        this.shopOpen = false;
        this.earthSurface = surface;
    }

    /**
     * Handle key pressed.
     *
     * @param event the event
     */
    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.P) {
            this.paused = !this.paused;
            return;
        }

        if (event.getCode() == KeyCode.M) {
            this.earthSurface.pauseGameLoop();
            new GameManager(this.earthSurface.getPrimaryStage(), 2);
        }

        if (event.getCode() == KeyCode.SPACE) {
            this.interacted = !this.interacted;
            return;
        }

        if (event.getCode() == KeyCode.ESCAPE) {
            this.shopOpen = false;
        }

        if (!this.paused) {
            switch (event.getCode()) {
                case W -> {
                    this.wDown = true;
                }
                case A -> {
                    this.aDown = true;
                }
                case S -> {
                    this.sDown = true;
                }
                case D -> {
                    this.dDown = true;
                }
            }
            this.updateDirection();
        }
    }

    /**
     * Handle key released.
     *
     * @param event the event
     */
    public void handleKeyReleased(KeyEvent event) {
        if (!this.paused) {
            switch (event.getCode()) {
                case W -> {
                    this.wDown = false;
                }
                case A -> {
                    this.aDown = false;
                }
                case S -> {
                    this.sDown = false;
                }
                case D -> {
                    this.dDown = false;
                }
            }
            this.updateDirection();
        }
    }

    /**
     * Update direction.
     */
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

    /**
     * Gets current direction.
     *
     * @return the current direction
     */
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    /**
     * Is paused boolean.
     *
     * @return the boolean
     */
    public boolean isPaused() {
        return this.paused;
    }

    /**
     * Sets paused.
     *
     * @param paused the paused
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * Is moving boolean.
     *
     * @return the boolean
     */
    public boolean isMoving() {
        return this.isMoving;
    }

    /**
     * Sets interacted.
     *
     * @param interacted the interacted
     */
    public void setInteracted(boolean interacted) {
        this.interacted = interacted;
    }

    /**
     * Is interacted boolean.
     *
     * @return the boolean
     */
    public boolean isInteracted() {
        return this.interacted;
    }

    /**
     * Is shop open boolean.
     *
     * @return the boolean
     */
    public boolean isShopOpen() {
        return this.shopOpen;
    }

    /**
     * Sets shop open.
     *
     * @param shopOpen the shop open
     */
    public void setShopOpen(boolean shopOpen) {
        this.shopOpen = shopOpen;
    }
}
