package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

/**
 * Maid.
 */
public class Maid extends NPC {

    /**
     * Instantiates a new Maid.
     *
     * @param worldX  the world x
     * @param worldY  the world y
     * @param name    the name
     * @param manager the manager
     */
    public Maid(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/maid/maid", "GENERAL", 50, 1, manager);
    }
}
