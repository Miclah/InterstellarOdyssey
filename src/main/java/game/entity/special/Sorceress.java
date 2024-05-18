package game.entity.special;

import game.entity.NPC;
import game.state.GeneralManager;

public class Sorceress extends NPC {

    public Sorceress(int worldX, int worldY, String name, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/sorceress/sor", "GENERAL", 50, 1, manager);
    }
}
