package game.util;

import game.entity.Entity;
import game.entity.Player;
import game.state.GeneralManager;

public class Collision {
    private final int TILE_SIZE = 32;
    private final GeneralManager manager;
    private final Player player;

    public Collision(GeneralManager manager, Player player) {
        this.manager = manager;
        this.player = player;
    }

    public void check(Entity entity) {
        entity.setCollision(false);
        this.player.setCollision(false);
        int entityLeftX = (int) (entity.getWorldX() + entity.getCollisionRectangle().getX());
        int entityRightX = (int) (entity.getWorldX() + entity.getCollisionRectangle().getX() + entity.getCollisionRectangle().getWidth());
        int entityTopY = (int) (entity.getWorldY() + entity.getCollisionRectangle().getY());
        int entityBottomY = (int) (entity.getWorldY() + entity.getCollisionRectangle().getY() + entity.getCollisionRectangle().getHeight());

        int entityLeftCol = entityLeftX / this.TILE_SIZE;
        int entityRightCol = entityRightX / this.TILE_SIZE;
        int entityTopRow = entityTopY / this.TILE_SIZE;
        int entityBottomRow = entityBottomY / this.TILE_SIZE;

        int tilenum1, tilenum2;

        Direction direction = entity.getDirection();
        if (direction != null) {
            switch (direction) {
                case UP, UP_LEFT, UP_RIGHT:
                    entityTopRow = (entityTopY - entity.getBaseSpeed()) / this.TILE_SIZE;
                    tilenum1 = this.manager.getTileManager().getMapTile(entityLeftCol, entityTopRow);
                    tilenum2 = this.manager.getTileManager().getMapTile(entityRightCol, entityTopRow);
                    if (this.manager.getTileManager().getTile(tilenum1).isCollision() || this.manager.getTileManager().getTile(tilenum2).isCollision()) {
                        entity.setCollision(true);
                    }
                    break;
                case DOWN, DOWN_LEFT, DOWN_RIGHT:
                    entityBottomRow = (entityBottomY + entity.getBaseSpeed()) / this.TILE_SIZE;
                    tilenum1 = this.manager.getTileManager().getMapTile(entityLeftCol, entityBottomRow);
                    tilenum2 = this.manager.getTileManager().getMapTile(entityRightCol, entityBottomRow);
                    if (this.manager.getTileManager().getTile(tilenum1).isCollision() || this.manager.getTileManager().getTile(tilenum2).isCollision()) {
                        entity.setCollision(true);
                    }
                    break;
                case LEFT:
                    entityLeftCol = (entityLeftX - entity.getBaseSpeed()) / this.TILE_SIZE;
                    tilenum1 = this.manager.getTileManager().getMapTile(entityLeftCol, entityTopRow);
                    tilenum2 = this.manager.getTileManager().getMapTile(entityLeftCol, entityBottomRow);
                    if (this.manager.getTileManager().getTile(tilenum1).isCollision() || this.manager.getTileManager().getTile(tilenum2).isCollision()) {
                        entity.setCollision(true);
                    }
                    break;
                case RIGHT:
                    entityRightCol = (entityRightX + entity.getBaseSpeed()) / this.TILE_SIZE;
                    tilenum1 = this.manager.getTileManager().getMapTile(entityRightCol, entityTopRow);
                    tilenum2 = this.manager.getTileManager().getMapTile(entityRightCol, entityBottomRow);
                    if (this.manager.getTileManager().getTile(tilenum1).isCollision() || this.manager.getTileManager().getTile(tilenum2).isCollision()) {
                        entity.setCollision(true);
                    }
                    break;
            }
        }

        for (Entity otherEntity : this.manager.getEntities()) {
            if (otherEntity != entity && this.rectanglesOverlap (entity, otherEntity)) {
                entity.setCollision(true);
                System.out.println("Entity: " + entity.getName() + " collides with " + otherEntity.getName());
                break;
            }
        }
    }

    private boolean rectanglesOverlap(Entity entity1, Entity entity2) {
        double left1 = entity1.getWorldX() + entity1.getCollisionRectangle().getX();
        double right1 = left1 + entity1.getCollisionRectangle().getWidth();
        double top1 = entity1.getWorldY() + entity1.getCollisionRectangle().getY();
        double bottom1 = top1 + entity1.getCollisionRectangle().getHeight();

        double left2 = entity2.getWorldX() + entity2.getCollisionRectangle().getX();
        double right2 = left2 + entity2.getCollisionRectangle().getWidth();
        double top2 = entity2.getWorldY() + entity2.getCollisionRectangle().getY();
        double bottom2 = top2 + entity2.getCollisionRectangle().getHeight();

        return !(right1 <= left2 || left1 >= right2 || bottom1 <= top2 || top1 >= bottom2);
    }
}
