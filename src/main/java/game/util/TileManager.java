package game.util;

import game.entity.NPC;
import game.entity.Player;
import game.tile.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *  TileManager class responsible for managing tile creation and drawing of tiles
 */
public class TileManager {
    /**
     * The Tiles.
     */
    private final Tile[] tiles;
    /**
     * The Map tiles.
     */
    private final int[][] mapTiles;
    /**
     * The Tile size.
     */
    private final int tileSize = 32;
    /**
     * The Max world col.
     */
    private final int maxWorldCol = 100;
    /**
     * The Max world row.
     */
    private final int maxWorldRow = 100;
    /**
     * The Is in player view.
     */
    private boolean isInPlayerView = false;

    /**
     * Instantiates a new Tile manager.
     */
    public TileManager() {
        this.tiles = new Tile[4];
        this.getImages();
        this.mapTiles = new int[this.maxWorldCol][this.maxWorldRow];
        this.loadMap();
    }

    /**
     * Gets tile images
     * In case of water tile, sets its collision property to true
     */
    private void getImages() {
        List<String> tileName = Arrays.asList("grass", "mud", "stone", "water");
        for (int i = 0; i < this.tiles.length; i++) {
            String filename = String.format("/textures/tiles/%s.png", tileName.get(i));
            Image image = new Image(filename);
            if (image.isError()) {
                System.out.println("File couldn't be loaded");
            } else {
                this.tiles[i] = new Tile();
                this.tiles[i].setImage(image);
                if (filename.contains("water")) {
                    this.tiles[i].setColision(true);
                }
            }
        }
    }

    /**
     * Loads map and assigns a texture to each tile based on the number in the inputFile
     */
    private void loadMap() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/textures/files/mapEarth.txt");
            Scanner scanner = new Scanner(inputStream);

            int col = 0;
            int row = 0;

            while (col < this.maxWorldCol && row < this.maxWorldRow) {
                String line = scanner.nextLine();
                while (col < this.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    this.mapTiles[col][row] = num;
                    col++;
                }
                if (col == this.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            scanner.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws tiles if they are in player view(Inside our game window)
     *
     * @param gc     the gc
     * @param player the player
     * @param npcs   the npcs
     */
    public void draw(GraphicsContext gc, Player player, ArrayList<NPC> npcs) {
        int worldCol = 0;
        int worldRow = 0;
        this.isInPlayerView = false;

        while (worldCol < this.maxWorldCol && worldRow < this.maxWorldRow) {
            int tileNum = this.mapTiles[worldCol][worldRow];
            int worldX = worldCol * this.tileSize;
            int worldY = worldRow * this.tileSize;
            int screenX = (int)(worldX - player.getWorldX() + player.getScreenX());
            int screenY = (int)(worldY - player.getWorldY() + player.getScreenY());

            boolean isVisible = (worldX + this.tileSize > player.getWorldX() - player.getScreenX() &&
                    worldX - this.tileSize < player.getWorldX() + player.getScreenX() + 48 &&
                    worldY + this.tileSize > player.getWorldY() - player.getScreenY() &&
                    worldY - this.tileSize < player.getWorldY() + player.getScreenY() + 48);

            if (isVisible) {
                gc.drawImage(this.tiles[tileNum].getImage(), screenX, screenY);
                this.isInPlayerView = true;
            }

            for (NPC npc : npcs) {
                double npcScreenX = screenX + npc.getWorldX() - worldX;
                double npcScreenY = screenY + npc.getWorldY() - worldY;

                if (isVisible) {
                    npc.getCurrentFrame().setVisible(true);
                    npc.getCurrentFrame().setTranslateX(npcScreenX);
                    npc.getCurrentFrame().setTranslateY(npcScreenY);
                }
            }
            worldCol++;
            if (worldCol == this.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    /**
     * Gets tile size.
     *
     * @return the tile size
     */
    public int getTileSize() {
        return this.tileSize;
    }

    /**
     * Gets map tile.
     *
     * @param number1 the number 1
     * @param number2 the number 2
     * @return the map tile
     */
    public int getMapTile(int number1, int number2) {
        return this.mapTiles[number1][number2];
    }

    /**
     * Gets tile.
     *
     * @param number the number
     * @return the tile
     */
    public Tile getTile(int number) {
        return this.tiles[number];
    }

    /**
     * Is in player view boolean.
     *
     * @return the boolean
     */
    public boolean isInPlayerView() {
        return this.isInPlayerView;
    }
}
