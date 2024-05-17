package game.util;

import game.entity.Entity;
import game.state.GeneralManager;

public class Collision {
    private final int TILE_SIZE = 32;
    private final GeneralManager manager;

    public Collision(GeneralManager manager) {
        this.manager = manager;
    }

    public void check(Entity entity) {
        int entityLeftX = (int) (entity.getWorldX() + entity.getCollisionRectangle().getX());
        int enitityRightX = (int) (entity.getWorldX() + entity.getCollisionRectangle().getX() + entity.getCollisionRectangle().getWidth());
        int entityTopY = (int) (entity.getWorldY() + entity.getCollisionRectangle().getY());
        int entityBottomY = (int) (entity.getWorldY() + entity.getCollisionRectangle().getY() + entity.getCollisionRectangle().getHeight());

        int entityLeftCol = entityLeftX / this.TILE_SIZE;
        int enittyRightCol = enitityRightX / this.TILE_SIZE;
        int entityTopRow = entityTopY / this.TILE_SIZE;
        int entityBottomRow = entityBottomY / this.TILE_SIZE;

        int tilenum1, tilenum2;

        switch (entity.getDirection()) {
            case UP, UP_LEFT, UP_RIGHT:
                entityTopRow = (entityTopY - entity.getBaseSpeed ()) / this.manager.getTileManager ().getTileSize ();
                tilenum1 = this.manager.getTileManager ().getMapTile (entityLeftCol, entityTopRow);
                tilenum2 = this.manager.getTileManager ().getMapTile (enittyRightCol, entityTopRow);
                if (this.manager.getTileManager ().getTile (tilenum1).isColision () || this.manager.getTileManager ().getTile (tilenum2).isColision ()) {
                    entity.setCollision (true);
                }
                break;
            case DOWN, DOWN_LEFT, DOWN_RIGHT:
                entityBottomRow = (entityBottomY + entity.getBaseSpeed ()) / this.manager.getTileManager ().getTileSize ();
                tilenum1 = this.manager.getTileManager ().getMapTile (entityLeftCol, entityBottomRow);
                tilenum2 = this.manager.getTileManager ().getMapTile (enittyRightCol, entityBottomRow);
                if (this.manager.getTileManager ().getTile (tilenum1).isColision () || this.manager.getTileManager ().getTile (tilenum2).isColision ()) {
                    entity.setCollision (true);
                }
                break;
            case LEFT:
                entityLeftCol = (entityLeftX - entity.getBaseSpeed ()) / this.manager.getTileManager ().getTileSize ();
                tilenum1 = this.manager.getTileManager ().getMapTile (entityLeftCol, entityTopRow);
                tilenum2 = this.manager.getTileManager ().getMapTile (entityLeftCol, entityBottomRow);
                if (this.manager.getTileManager ().getTile (tilenum1).isColision () || this.manager.getTileManager ().getTile (tilenum2).isColision ()) {
                    entity.setCollision (true);
                }
                break;
            case RIGHT:
                enittyRightCol = (enitityRightX + entity.getBaseSpeed ()) / this.manager.getTileManager ().getTileSize ();
                tilenum1 = this.manager.getTileManager ().getMapTile (enittyRightCol, entityTopRow);
                tilenum2 = this.manager.getTileManager ().getMapTile (enittyRightCol, entityBottomRow);
                if (this.manager.getTileManager ().getTile (tilenum1).isColision () || this.manager.getTileManager ().getTile (tilenum2).isColision ()) {
                    entity.setCollision (true);
                }
                break;
        }
    }
}
