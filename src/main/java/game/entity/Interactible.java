package game.entity;

import javafx.scene.canvas.Canvas;

public interface Interactible {
    // follow player
    // react to player changes
    void interact(Canvas canvas, Player player);
}
