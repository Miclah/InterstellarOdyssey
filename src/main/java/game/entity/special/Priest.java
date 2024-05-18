package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

public class Priest extends NPC {

    public Priest(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/priest/priest", "GENERAL", 50, 1, manager);
    }
}
