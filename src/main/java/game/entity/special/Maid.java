package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

public class Maid extends NPC {

    public Maid(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/maid/maid", "GENERAL", 50, 1, manager);
    }
}
