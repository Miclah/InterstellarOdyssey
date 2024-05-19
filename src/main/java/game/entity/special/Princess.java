package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

/**
 * Princess.
 */
public class Princess extends NPC {

    /**
     * Instantiates a new Princess.
     *
     * @param worldX  the world x
     * @param worldY  the world y
     * @param name    the name
     * @param manager the manager
     */
    public Princess(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/princess/prin", "GENERAL", 50, 1, manager);
    }
}
