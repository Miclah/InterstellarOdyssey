package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

public class Hunter extends NPC {

    public Hunter(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/hunter/hunter", "GENERAL", 50, 1, manager);
    }
}
