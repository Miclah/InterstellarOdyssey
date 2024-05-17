package game.state;

import game.entity.Entity;
import game.util.Collision;
import game.util.TileManager;

import java.util.ArrayList;
import java.util.Collections;

public class GeneralManager {

    private TileManager tileManager;
    Collision collision = new Collision (this);
    private ArrayList<Entity> entities = new ArrayList<> ();

    public void createTileManger() {
        this.tileManager = new TileManager ();
    }

    public TileManager getTileManager() {
        return this.tileManager;
    }

    public Collision getCollision() {
        return this.collision;
    }

    public void addEnitity(Entity entity) {
        this.entities.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(this.entities);
    }

}
