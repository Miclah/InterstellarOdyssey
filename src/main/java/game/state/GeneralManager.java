package game.state;

import game.entity.Entity;
import game.entity.Player;
import game.util.Collision;
import game.util.TileManager;
import java.util.ArrayList;

public class GeneralManager {

    private TileManager tileManager;
    private Collision collision;
    private ArrayList<Entity> entities = new ArrayList<> ();
    private Player player;
    private KeyManager keyManager;

    public void createTileManger() {
        this.tileManager = new TileManager ();
    }

    public TileManager getTileManager() {
        return this.tileManager;
    }

    public Collision getCollision() {
        return this.collision;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(this.entities);
    }

    public void createCollision(Player player) {
        this.collision = new Collision (this, player);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public KeyManager getKeyManager() {
        return this.keyManager;
    }
}
