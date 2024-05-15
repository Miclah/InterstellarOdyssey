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

public class TileManager {
    private final Tile[] tiles;
    private final int[][] mapTiles;
    private final int tileSize = 32;
    private final int maxWorldCol = 100;
    private final int maxWorldRow = 100;
    private boolean isInPlayerView = false;

    public TileManager() {
        this.tiles = new Tile[4];
        this.getImages();
        this.mapTiles = new int[this.maxWorldCol][this.maxWorldRow];
        this.loadMap();
    }

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
            }
        }
    }

    private void loadMap() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/textures/files/mapEarth.txt");
            Scanner scanner = new Scanner(inputStream);

            int col = 0;
            int row = 0;

            while (col < this.maxWorldCol && row < this.maxWorldRow) {
                String line = scanner.nextLine();
                while (col < maxWorldCol) {
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

    public void draw(GraphicsContext gc, Player player, ArrayList<NPC> npcs) {
        int worldCol = 0;
        int worldRow = 0;
        this.isInPlayerView = false;

        while (worldCol < this.maxWorldCol && worldRow < this.maxWorldRow) {
            int tileNum = this.mapTiles[worldCol][worldRow];
            int worldX = worldCol * this.tileSize;
            int worldY = worldRow * this.tileSize;
            int screenX = (int) (worldX - player.getWorldX() + player.getScreenX());
            int screenY = (int) (worldY - player.getWorldY() + player.getScreenY());

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

    public int getTileSize() {
        return this.tileSize;
    }

    public boolean isInPlayerView() {
        return this.isInPlayerView;
    }
}
