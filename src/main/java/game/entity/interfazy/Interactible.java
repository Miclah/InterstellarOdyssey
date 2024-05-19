package game.entity.interfazy;

import game.entity.Player;
import javafx.scene.canvas.Canvas;

public interface Interactible {
    // follow player
    // react to player changes
    void interact();
    void showZone(Canvas canvas, Player player);
}
