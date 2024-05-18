package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

public class Princess extends NPC {

    public Princess(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/princess/prin", "GENERAL", 50, 1, manager);
    }
}
