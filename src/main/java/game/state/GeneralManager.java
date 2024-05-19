package game.state;

import game.entity.Entity;
import game.entity.Player;
import game.util.Collision;
import game.util.TileManager;
import java.util.ArrayList;

/**
 * GeneralManager class which holds most instances in the game for easier management
 */
public class GeneralManager {

    /**
     * The Tile manager.
     */
    private TileManager tileManager;
    /**
     * The Collision.
     */
    private Collision collision;
    /**
     * The Entities.
     */
    private ArrayList<Entity> entities = new ArrayList<> ();
    /**
     * The Player.
     */
    private Player player;
    /**
     * The Key manager.
     */
    private KeyManager keyManager;
    /**
     * The constant playerName.
     */
    private static String playerName;
    /**
     * The constant skinChoice.
     */
    private static int skinChoice;

    /**
     * Create tile manger.
     */
    public void createTileManger() {
        this.tileManager = new TileManager ();
    }

    /**
     * Gets tile manager.
     *
     * @return the tile manager
     */
    public TileManager getTileManager() {
        return this.tileManager;
    }

    /**
     * Gets collision.
     *
     * @return the collision
     */
    public Collision getCollision() {
        return this.collision;
    }

    /**
     * Add entity.
     *
     * @param entity the entity
     */
    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    /**
     * Sets key manager.
     *
     * @param keyManager the key manager
     */
    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    /**
     * Gets entities.
     *
     * @return the entities
     */
    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(this.entities);
    }

    /**
     * Create collision.
     *
     * @param player the player
     */
    public void createCollision(Player player) {
        this.collision = new Collision (this, player);
        this.player = player;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets key manager.
     *
     * @return the key manager
     */
    public KeyManager getKeyManager() {
        return this.keyManager;
    }

    /**
     * Sets up player.
     *
     * @param name   the name
     * @param choice the choice
     */
    public static void setUpPlayer(String name, int choice) {
        playerName = name;
        skinChoice = choice;
    }

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets skin choice.
     *
     * @return the skin choice
     */
    public int getSkinChoice() {
        return skinChoice;
    }
}
