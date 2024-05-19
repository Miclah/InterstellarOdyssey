package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

/**
 * Sorceress.
 */
public class Sorceress extends NPC {

    /**
     * Instantiates a new Sorceress.
     *
     * @param worldX  the world x
     * @param worldY  the world y
     * @param name    the name
     * @param manager the manager
     */
    public Sorceress(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/sorceress/sor", "GENERAL", 50, 1, manager);
    }
}
