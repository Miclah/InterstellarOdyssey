package game.util;

import game.entity.NPC;
import game.entity.NormalNPC;
import game.entity.special.Hunter;
import game.entity.special.Maid;
import game.entity.special.Priest;
import game.entity.special.Princess;
import game.entity.special.Sorceress;
import game.io.Loader;
import game.state.GeneralManager;
import java.util.List;
import java.util.Random;

/**
 * Class responsible for randomly creating NPC's
 */
public class NpcCreator {
    /**
     * The constant MIN_DISTANCE_FROM_LAKE.
     */
    private static final int MIN_DISTANCE_FROM_LAKE = 2;
    /**
     * The constant MIN_DISTANCE_FROM_EDGE.
     */
    private static final int MIN_DISTANCE_FROM_EDGE = 5;
    /**
     * The constant random.
     */
    private static Random random;
    /**
     * The constant sorceressSpawned.
     */
    private static boolean sorceressSpawned = false;
    /**
     * The constant princessSpawned.
     */
    private static boolean princessSpawned = false;
    /**
     * The constant maidSpawned.
     */
    private static boolean maidSpawned = false;
    /**
     * The constant priestSpawned.
     */
    private static boolean priestSpawned = false;
    /**
     * The constant hunterSpawned.
     */
    private static boolean hunterSpawned = false;
    /**
     * The constant normalYoungCount.
     */
    private static int normalYoungCount = 0;
    /**
     * The constant normalOldCount.
     */
    private static int normalOldCount = 0;

    /**
     * The constant maleNames.
     */
    private static final List<String> MALE_NAMES;
    /**
     * The constant femaleNames.
     */
    private static final List<String> FEMALE_NAMES;

    static {
        random = new Random();
        MALE_NAMES = Loader.loadNames("src/main/resources/textures/files/male_names.bin");
        FEMALE_NAMES = Loader.loadNames("src/main/resources/textures/files/female_names.bin");
    }

    /**
     * Create an NPC on a random location within the world map
     *
     * @param manager     the manager
     * @param tileManager the tile manager
     * @return the npc
     */
    public static NPC createRandomNPC(GeneralManager manager, TileManager tileManager) {
        int tileSize = tileManager.getTileSize();
        int worldX;
        int worldY;

        do {
            worldX = (random.nextInt(100 - 2 * MIN_DISTANCE_FROM_EDGE) + MIN_DISTANCE_FROM_EDGE) * tileSize;
            worldY = (random.nextInt(100 - 2 * MIN_DISTANCE_FROM_EDGE) + MIN_DISTANCE_FROM_EDGE) * tileSize;
        } while (isNearLake(worldX, worldY, tileManager, tileSize));

        if (!sorceressSpawned) {
            sorceressSpawned = true;
            return new Sorceress(worldX, worldY, "Hermiona", manager);
        }
        if (!princessSpawned) {
            princessSpawned = true;
            return new Princess(worldX, worldY, "Aurora", manager);
        }
        if (!maidSpawned) {
            maidSpawned = true;
            return new Maid(worldX, worldY, "Rem", manager);
        }
        if (!priestSpawned) {
            priestSpawned = true;
            return new Priest(worldX, worldY, "Pope", manager);
        }
        if (!hunterSpawned) {
            hunterSpawned = true;
            return new Hunter(worldX, worldY, "Legolas", manager);
        }

        String npcType;
        String man = "man/man";
        String woman = "woman/woman";
        if ((normalYoungCount + normalOldCount) < 0.6 * (normalYoungCount + normalOldCount + 1)) {
            if (random.nextBoolean()) {
                npcType = "young/" + man;
                return new NormalNPC(worldX, worldY, getRandomName(MALE_NAMES), npcType, manager);
            } else {
                npcType = "young/" + woman;
                return new NormalNPC(worldX, worldY, getRandomName(FEMALE_NAMES), npcType, manager);
            }
        } else {
            if (random.nextBoolean()) {
                npcType = "old/" + man;
                return new NormalNPC(worldX, worldY, getRandomName(MALE_NAMES), npcType, manager);
            } else {
                npcType = "old/" + woman;
                return new NormalNPC(worldX, worldY, getRandomName(FEMALE_NAMES), npcType, manager);
            }
        }
    }

    /**
     * Checks whether a location is too close to a water tile, if yes returns true
     *
     * @param worldX      the world x
     * @param worldY      the world y
     * @param tileManager the tile manager
     * @param tileSize    the tile size
     * @return the boolean
     */
    private static boolean isNearLake(int worldX, int worldY, TileManager tileManager, int tileSize) {
        int col = worldX / tileSize;
        int row = worldY / tileSize;

        for (int i = -MIN_DISTANCE_FROM_LAKE; i <= MIN_DISTANCE_FROM_LAKE; i++) {
            for (int j = -MIN_DISTANCE_FROM_LAKE; j <= MIN_DISTANCE_FROM_LAKE; j++) {
                int tileNum = tileManager.getMapTile(col + i, row + j);
                if (tileManager.getTile(tileNum).isCollision()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets random name.
     *
     * @param names the names
     * @return the random name
     */
    private static String getRandomName(List<String> names) {
        if (names.isEmpty()) {
            throw new RuntimeException("No more names available.");
        }
        int index = random.nextInt(names.size());
        return names.remove(index);
    }
}
