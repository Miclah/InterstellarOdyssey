package game.entity;

import game.state.GeneralManager;

public class NormalNPC extends NPC {

    public NormalNPC(int worldX, int worldY, String name, String pathToImage, GeneralManager manager) {
        super (worldX, worldY, name, "npc/general/human/normal/" + pathToImage, "GENERAL", 50, 1, manager);
    }
}
