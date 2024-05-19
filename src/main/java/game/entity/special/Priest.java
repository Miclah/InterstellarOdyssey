package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

/**
 * Priest.
 */
public class Priest extends NPC {

    /**
     * Instantiates a new Priest.
     *
     * @param worldX  the world x
     * @param worldY  the world y
     * @param name    the name
     * @param manager the manager
     */
    public Priest(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/priest/priest", "GENERAL", 50, 1, manager);
    }
}
