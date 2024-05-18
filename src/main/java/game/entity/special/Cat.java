package game.entity.special;

import game.entity.NPC;
import game.entity.Player;
import game.gui.DialogBox;
import game.state.GeneralManager;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Cat extends NPC {

    public Cat(int worldX, int worldY, String name, GeneralManager manager) {
        super(worldX, worldY, name, "npc/general/cat" + Cat.getRandomCatImagePath(), "GENERAL", 50, 1   , manager);
        System.out.println ("npc/general/cat" + Cat.getRandomCatImagePath());
    }

    @Override
    public void wander() {
        Player player = super.getManager().getPlayer();
        double distanceToPlayer = Math.sqrt(Math.pow(player.getWorldX() - super.getWorldX(), 2) +
                Math.pow(player.getWorldY() - super.getWorldY(), 2));
        super.getManager().getCollision().check(this);
        if (distanceToPlayer <= 120) {
            double deltaX = super.getWorldX() - player.getWorldX();
            double deltaY = super.getWorldY() - player.getWorldY();
            double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double normalizedX = deltaX / magnitude;
            double normalizedY = deltaY / magnitude;
            double newX = super.getWorldX() + normalizedX * 200;
            double newY = super.getWorldY() + normalizedY * 200;

            super.setWorldX((int) newX);
            super.setWorldY((int) newY);
        } else {
            super.wander();
        }
    }

    public static String getRandomCatImagePath() {
        Random random = new Random();
        double chance = random.nextDouble();
        System.out.println (chance);
        if (chance <= 0.3) {
            return "/white/cat";
        } else {
            return "/orange/cat";
        }
    }

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

    @Override
    public String getMessage() {
        return "Meow";
    }
}
