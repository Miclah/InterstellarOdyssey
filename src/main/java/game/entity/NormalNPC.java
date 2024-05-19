package game.entity;

import game.state.GeneralManager;

/**
 * The Normal npc.
 */
public class NormalNPC extends NPC {

    /**
     * Instantiates a new Normal npc.
     *
     * @param worldX      the world x
     * @param worldY      the world y
     * @param name        the name
     * @param pathToImage the path to image
     * @param manager     the manager
     */
    public NormalNPC(int worldX, int worldY, String name, String pathToImage, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/normal/" + pathToImage, "GENERAL", 50, 1, manager);
    }
}
