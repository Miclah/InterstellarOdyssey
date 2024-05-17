    package game.state;

    import game.util.Collision;
    import game.util.TileManager;

    public class GeneralManager {

        private TileManager tileManager;
        Collision collision = new Collision(this);


        public void createTileManger() {
            this.tileManager = new TileManager();
        }
        public TileManager getTileManager() {
            return this.tileManager;
        }

        public Collision getCollision() {
            return this.collision;
        }
    }
