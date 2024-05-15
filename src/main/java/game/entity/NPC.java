package game.entity;

import game.gui.DialogBox;
import game.io.Loader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class NPC extends Entity implements Interactible {

    private HashMap<RelationshipType, ArrayList<String>> dialogue;
    private int relation;

    public NPC(int worldX, int worldY, String name, String pathToImage, String npcType, int relation) {
        super(worldX, worldY, name, pathToImage);
        this.relation = relation;
        this.dialogue = Loader.loadDialogues("\\textures\\files\\dialogue.json", npcType);
    }

    public HashMap<RelationshipType, ArrayList<String>> getDialogue() {
        return this.dialogue; // vytvorit novy hashmap inak porusi zapuzdrenie?
    }

    public void setDialogue(HashMap<RelationshipType, ArrayList<String>> dialogue) {
        this.dialogue = dialogue;
    }

    public void addDialog(RelationshipType type, ArrayList<String> dialogue) {
        this.dialogue.put(type, dialogue);
    }

    public void addDialog(RelationshipType type, String dialog) {
        ArrayList<String> existingDialogues = this.dialogue.computeIfAbsent(type, k -> new ArrayList<>());
        existingDialogues.add(dialog);
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

    public RelationshipType getRelationType() {
        return RelationshipType.values()[this.relation];
    }

    @Override
    public void interact(Canvas canvas) {
        double dialogX = this.getWorldX() + 50;
        double dialogY = this.getWorldY() - 50;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        DialogBox.draw(gc, this.getMessage(), dialogX, dialogY);
    }

    public String getMessage() {
        RelationshipType currentType = this.getRelationType();
        ArrayList<String> messages = this.dialogue.get(currentType);
        if (!messages.isEmpty()) {
            int randomIndex = (int) (Math.random() * messages.size());
            return messages.get(randomIndex);
        } else {
            return "";
        }
    }
}
