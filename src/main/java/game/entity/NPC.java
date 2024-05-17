package game.entity;

import game.gui.DialogBox;
import game.io.Loader;
import game.state.GeneralManager;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class NPC extends Entity implements Talkable, Wandarable {

    private HashMap<RelationshipType, ArrayList<String>> dialogue;
    private int relation;
    private boolean alreadyDisplayed = false;
    private DialogBox dialogBox;

    public NPC(int worldX, int worldY, String name, String pathToImage, String npcType, int relation, int speed, GeneralManager manager) {
        super(worldX, worldY, name, pathToImage, speed, manager);
        this.relation = relation;
        this.dialogue = Loader.loadDialogues("src/main/resources/textures/files/dialogue.json", npcType);
    }

    public HashMap<RelationshipType, ArrayList<String>> getDialogue() {
        return (HashMap<RelationshipType, ArrayList<String>>) Collections.unmodifiableMap(this.dialogue);
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
        int interval = 100 / (RelationshipType.values().length - 1);
        int index = this.relation / interval;
        return RelationshipType.values()[index];
    }

    @Override
    public void talk(Pane pane, Player player) {
        double npcScreenX = super.getWorldX() - player.getWorldX() + player.getScreenX();
        double npcScreenY = super.getWorldY() - player.getWorldY() + player.getScreenY();
        if (!this.alreadyDisplayed) {
            this.dialogBox = new DialogBox(pane, this.getMessage(), "textures/dialogue/5.png", npcScreenX, npcScreenY);
            this.alreadyDisplayed = true;
        }
        this.dialogBox.setCoordinates(npcScreenX, npcScreenY);
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

    @Override
    public void resetTalk() {
        this.alreadyDisplayed = false;
    }

    @Override
    public void wander() {
        double newX = super.getWorldX() + (Math.random() * 5 - 1);
        double newY = super.getWorldY() + (Math.random() * 5 - 1);
        super.setWorldX(newX);
        super.setWorldY(newY);
    }
}
