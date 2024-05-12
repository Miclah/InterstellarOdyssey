package game.entity;

import java.util.HashMap;

public abstract class NPC extends Entity implements Interactible{

    private HashMap<RelationshipType, String> dialogue;
    private int relation;

    public NPC(int worldX, int worldY, String name, String pathToImage, HashMap<RelationshipType, String> dialogue, int relation) {
        super(worldX, worldY, name, "");
        this.dialogue = dialogue;
        this.relation = relation;
    }

    public HashMap<RelationshipType, String> getDialogue() {
        return this.dialogue;
    }

    public void setDialogue(HashMap<RelationshipType, String> dialogue) {
        this.dialogue = dialogue;
    }

    public void setRelation(int relation) {
        if (this.relation + relation > 100) {
            this.relation = 100;
        } else if (this.relation + relation < 0) {
            this.relation = 0;
        } else {
            this.relation = relation;
        }
    }

    public RelationshipType getType() {
        return RelationshipType.values()[this.relation];
    }

    @Override
    public void interact() {

    }
}
