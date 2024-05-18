package game.entity;

import game.entity.interfazy.Talkable;
import game.entity.interfazy.Wandarable;
import game.gui.DialogBox;
import game.io.Loader;
import game.state.GeneralManager;
import game.util.Direction;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public abstract class NPC extends Entity implements Talkable, Wandarable {

    private HashMap<RelationshipType, ArrayList<String>> dialogue;
    private int relation;
    private boolean alreadyDisplayed = false;
    private DialogBox dialogBox;
    private int changeTimer;
    private boolean isResting;
    private int restTimer;
    private final Random random;

    public NPC(int worldX, int worldY, String name, String pathToImage, String npcType, int relation, int speed, GeneralManager manager) {
        super(worldX, worldY, name, pathToImage, speed, manager);
        this.relation = relation;
        this.dialogue = Loader.loadDialogues("src/main/resources/textures/files/dialogue.json", npcType);
        this.changeTimer = 0;
        this.random = new Random();
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
            this.dialogBox = new DialogBox(pane, this.getMessage(), "textures/dialogue/dialogue.png", npcScreenX, npcScreenY);
            this.alreadyDisplayed = true;
        }
        this.dialogBox.setCoordinatesHumans (npcScreenX, npcScreenY);
    }

    @Override
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
        if (this.isResting) {
            int restInterval = 60;
            if (this.restTimer >= restInterval) {
                this.isResting = false;
                this.restTimer = 0;
            } else {
                this.performIdleAction ();
                this.restTimer++;
                return;
            }
        }

        int changeInterval = 120;
        if (this.changeTimer >= changeInterval) {
            Direction nextDirection = this.getNextDirection();
            super.setDirection(nextDirection);
            this.changeTimer = 0;
        } else {
            this.changeTimer++;
        }

        super.move();
        super.getManager().getCollision().check(this);
    }

    private Direction getNextDirection() {
        Direction[] directions = Direction.values();
        Direction currentDirection = super.getDirection();

        Direction newDirection;
        do {
            newDirection = directions[this.random.nextInt(directions.length)];
        } while (currentDirection != null && newDirection == currentDirection.opposite());

        if (this.random.nextInt(10) < 2) {
            this.isResting = true;
            return null;
        }

        return newDirection;
    }

    private void performIdleAction() {
        if (this.random.nextInt(2) == 0) {
            Direction[] directions = Direction.values();
            super.setDirection(directions[this.random.nextInt(directions.length)]);
        } else {
            super.setDirection(null);
        }
    }

    public boolean isAlreadyDisplayed() {
        return this.alreadyDisplayed;
    }

    public void setAlreadyDisplayed(boolean alreadyDisplayed) {
        this.alreadyDisplayed = alreadyDisplayed;
    }

    public void setDialogBox(DialogBox dialogBox) {
        this.dialogBox = dialogBox;
    }

    public DialogBox getDialogBox() {
        return this.dialogBox;
    }
}
