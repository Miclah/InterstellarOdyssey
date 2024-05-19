package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

/**
 * Hunter
 */
public class Hunter extends NPC {

    /**
     * Instantiates a new Hunter.
     *
     * @param worldX  the world x
     * @param worldY  the world y
     * @param name    the name
     * @param manager the manager
     */
    public Hunter(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/hunter/hunter", "GENERAL", 50, 1, manager);
    }
}
