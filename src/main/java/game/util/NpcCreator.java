package game.util;

import game.entity.NPC;
import game.entity.NormalNPC;
import game.entity.special.*;
import game.state.GeneralManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NpcCreator {
    private static final int MIN_DISTANCE_FROM_LAKE = 2;
    private static final int MIN_DISTANCE_FROM_EDGE = 5;
    private static final Random random = new Random();
    private static boolean sorceressSpawned = false;
    private static boolean princessSpawned = false;
    private static boolean maidSpawned = false;
    private static boolean priestSpawned = false;
    private static boolean hunterSpawned = false;
    private static int normalYoungCount = 0;
    private static int normalOldCount = 0;

    private static final List<String> normalNpcNames = new ArrayList<>(Arrays.asList(
            "Aragorn", "Eowyn", "Thorin", "Galadriel", "Legolas", "Arwen", "Boromir", "Faramir",
            "Eomer", "Elrond", "Gimli", "Haldir", "Celeborn", "Glorfindel", "Luthien"
    ));

    public static NPC createRandomNPC(GeneralManager manager, TileManager tileManager) {
        int tileSize = tileManager.getTileSize();
        int worldX, worldY;

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
            } else {
                npcType = "young/" + woman;
            }
            normalYoungCount++;
        } else {
            if (random.nextBoolean()) {
                npcType = "old/" + man;
            } else {
                npcType = "old/" + woman;
            }
            normalOldCount++;
        }

        return new NormalNPC (worldX, worldY, getRandomName(), npcType, manager);
    }

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

    private static String getRandomName() {
        if (normalNpcNames.isEmpty()) {
            throw new RuntimeException("No more names available for normal NPCs");
        }
        int index = random.nextInt(normalNpcNames.size());
        return normalNpcNames.remove(index);
    }
}
