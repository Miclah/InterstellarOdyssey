package game.entity.special;

import game.entity.NPC;
import game.entity.Player;
import game.gui.DialogBox;
import game.state.GeneralManager;
import game.util.Direction;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Cat class who is a special NPC because it
 * runs away from the player once he is in close proximity
 */
public class Cat extends NPC {

    /**
     * Instantiates a new Cat.
     *
     * @param worldX  the world x
     * @param worldY  the world y
     * @param name    the name
     * @param manager the manager
     */
    public Cat(int worldX, int worldY, String name, GeneralManager manager) {
        super(worldX, worldY, name, "npc/general/cat" + Cat.getRandomCatImagePath(), "GENERAL", 50, 1   , manager);
    }

    /**
     * Makes cat wonder around similliar to normal NPCs,
     * but when a playetr gets too close it starts to run away
     */
    @Override
    public void wander() {
        Player player = super.getManager().getPlayer();
        double distanceToPlayer = Math.sqrt(Math.pow(player.getWorldX() - super.getWorldX(), 2) +
                Math.pow(player.getWorldY() - super.getWorldY(), 2));
        super.getManager().getCollision().check(this);

        if (distanceToPlayer <= 150) {
            double deltaX = super.getWorldX() - player.getWorldX();
            double deltaY = super.getWorldY() - player.getWorldY();
            double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double normalizedX = deltaX / magnitude;
            double normalizedY = deltaY / magnitude;

            double newX = super.getWorldX() + normalizedX * 15;
            double newY = super.getWorldY() + normalizedY * 15;

            double speedFactor = 0.1;
            double moveX = (newX - super.getWorldX()) * speedFactor;
            double moveY = (newY - super.getWorldY()) * speedFactor;

            if (Math.abs(moveX) > Math.abs(moveY)) {
                super.setDirection(moveX > 0 ? Direction.RIGHT : Direction.LEFT);
            } else {
                super.setDirection(moveY > 0 ? Direction.DOWN : Direction.UP);
            }

            super.setWorldX(super.getWorldX() + moveX);
            super.setWorldY(super.getWorldY() + moveY);
            super.move();
        } else {
            super.wander();
        }
    }

    /**
     * Based on a percentual chance of 70/30 creates either
     * an orange or a white cat
     *
     * @return the random cat image path
     */
    public static String getRandomCatImagePath() {
        Random random = new Random();
        double chance = random.nextDouble();
        if (chance <= 0.3) {
            return "/white/cat";
        } else {
            return "/orange/cat";
        }
    }

    /**
     * Makes a change to the talk method from parent,
     * changes its speechBubble image to smaller
     * and modifies its location to fit a smaller image(cat)
     *
     * @param pane   the pane
     * @param player the player
     */
    @Override
    public void talk(Pane pane, Player player) {
        double npcScreenX = super.getWorldX() - player.getWorldX() + player.getScreenX();
        double npcScreenY = super.getWorldY() - player.getWorldY() + player.getScreenY();
        if (!super.isAlreadyDisplayed()) {
            super.setDialogBox(new DialogBox(pane, this.getMessage(), "textures/dialogue/dialogue_short.png", npcScreenX, npcScreenY));
            super.setAlreadyDisplayed(true);
        }
        super.getDialogBox().setCoordinatesAnimals(npcScreenX, npcScreenY);
    }

    /**
     * Overrides the method to just return Meow,
     * since cats cant talk
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "Meow";
    }
}
