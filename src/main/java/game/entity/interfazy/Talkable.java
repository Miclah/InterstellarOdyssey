package game.entity.interfazy;

import game.entity.Player;
import javafx.scene.layout.Pane;

/**
 * The interface Talkable.
 */
public interface Talkable {
    /**
     * Allows an npc to talk by displaying a speech
     * bubble above their heads
     *
     * @param pane   the pane
     * @param player the player
     */
    void talk(Pane pane, Player player);

    /**
     * Resets the boolean so that an npc may
     * talk once again if its inside the playerView
     */
    void resetTalk();

    /**
     * Gets a random message based on the
     * relationship with player level
     *
     * @return the message
     */
    String getMessage();
}
