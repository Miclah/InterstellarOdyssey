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

/**
 * NPC class
 */
public abstract class NPC extends Entity implements Talkable, Wandarable {

    /**
     * The Dialogue.
     */
    private HashMap<RelationshipType, ArrayList<String>> dialogue;
    /**
     * The Relation.
     */
    private int relation;
    /**
     * The Already displayed.
     */
    private boolean alreadyDisplayed = false;
    /**
     * The Dialog box.
     */
    private DialogBox dialogBox;
    /**
     * The Change timer.
     */
    private int changeTimer;
    /**
     * The Is resting.
     */
    private boolean isResting;
    /**
     * The Rest timer.
     */
    private int restTimer;
    /**
     * The Random.
     */
    private final Random random;
    /**
     * The Rest counter.
     */
    private int restCounter;

    /**
     * Instantiates a new Npc.
     *
     * @param worldX      the world x
     * @param worldY      the world y
     * @param name        the name
     * @param pathToImage the path to image
     * @param npcType     the npc type
     * @param relation    the relation
     * @param speed       the speed
     * @param manager     the manager
     */
    public NPC(int worldX, int worldY, String name, String pathToImage, String npcType, int relation, int speed, GeneralManager manager) {
        super(worldX, worldY, name, pathToImage, speed, manager);
        this.relation = relation;
        this.dialogue = Loader.loadDialogues("src/main/resources/textures/files/dialogue.json", npcType);
        this.changeTimer = 0;
        this.random = new Random();
    }

    /**
     * Gets dialogue.
     *
     * @return the dialogue
     */
    public HashMap<RelationshipType, ArrayList<String>> getDialogue() {
        return (HashMap<RelationshipType, ArrayList<String>>)Collections.unmodifiableMap(this.dialogue);
    }

    /**
     * Sets dialogue.
     *
     * @param dialogue the dialogue
     */
    public void setDialogue(HashMap<RelationshipType, ArrayList<String>> dialogue) {
        this.dialogue = dialogue;
    }

    /**
     * Add dialog.
     *
     * @param type     the type
     * @param dialogue the dialogue
     */
    public void addDialog(RelationshipType type, ArrayList<String> dialogue) {
        this.dialogue.put(type, dialogue);
    }

    /**
     * Add dialog.
     *
     * @param type   the type
     * @param dialog the dialog
     */
    public void addDialog(RelationshipType type, String dialog) {
        ArrayList<String> existingDialogues = this.dialogue.computeIfAbsent(type, k -> new ArrayList<>());
        existingDialogues.add(dialog);
    }

    /**
     * Sets relation.
     *
     * @param relation the relation
     */
    public void setRelation(int relation) {
        if (this.relation + relation > 100) {
            this.relation = 100;
        } else if (this.relation + relation < 0) {
            this.relation = 0;
        } else {
            this.relation = relation;
        }
    }

    /**
     * Gets relation type based on the value of relation.
     *
     * @return the relation type
     */
    public RelationshipType getRelationType() {
        int interval = 100 / (RelationshipType.values().length - 1);
        int index = this.relation / interval;
        return RelationshipType.values()[index];
    }

    /**
     * Method called once npc is inside the playerView and it has not spoken yet.
     * Creates a new SpeechBublle which deletes itself after 3 seconds
     *
     * @param pane   the pane
     * @param player the player
     */
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

    /**
     * Gets a random message from dialogues message arraylist
     *
     * @return a random message
     */
    @Override
    public String getMessage() {
        RelationshipType currentType = this.getRelationType();
        ArrayList<String> messages = this.dialogue.get(currentType);
        if (!messages.isEmpty()) {
            int randomIndex = (int)(Math.random() * messages.size());
            return messages.get(randomIndex);
        } else {
            return "";
        }
    }

    /**
     * Resets the flag after dissapearing from the playerView
     */
    @Override
    public void resetTalk() {
        this.alreadyDisplayed = false;
    }

    /**
     * Makes an NPC wander randomly around with random rest inbetween
     */
    @Override
    public void wander() {
        if (this.isResting) {
            int restInterval = 60;
            if (this.restTimer >= restInterval) {
                this.isResting = false;
                this.restTimer = 0;
            } else {
                this.performIdleAction();
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

    /**
     * Gets a random next direction which NPC will head
     *
     * @return the next direction
     */
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

    /**
     * Makes an NPC look around when he is resting
     */
    private void performIdleAction() {
        if (this.restCounter <= 120) {
            if (this.restCounter % 40 == 0) {
                if (this.random.nextInt(2) == 0) {
                    Direction[] directions = Direction.values();
                    super.setDirection(directions[this.random.nextInt(directions.length)]);
                    super.changeFrame();
                } else {
                    super.setDirection(null);
                }
            }
            this.restCounter++;
        } else {
            this.restCounter = 0;
        }
    }

    /**
     * Is already displayed boolean.
     *
     * @return the boolean
     */
    public boolean isAlreadyDisplayed() {
        return this.alreadyDisplayed;
    }

    /**
     * Sets already displayed.
     *
     * @param alreadyDisplayed the already displayed
     */
    public void setAlreadyDisplayed(boolean alreadyDisplayed) {
        this.alreadyDisplayed = alreadyDisplayed;
    }

    /**
     * Sets dialog box.
     *
     * @param dialogBox the dialog box
     */
    public void setDialogBox(DialogBox dialogBox) {
        this.dialogBox = dialogBox;
    }

    /**
     * Gets dialog box.
     *
     * @return the dialog box
     */
    public DialogBox getDialogBox() {
        return this.dialogBox;
    }
}
