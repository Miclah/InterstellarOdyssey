package game.entity.interfazy;

import game.entity.Player;
import javafx.scene.canvas.Canvas;

/**
 * The interface Interactible.
 */
public interface Interactible {
    /**
     * Allows a player to interact with an npc
     */
    void interact();

    /**
     * Shows interaction zone around an npc, specifically shopkeepers
     *
     * @param canvas the canvas
     * @param player the player
     */
    void showZone(Canvas canvas, Player player);
}
