package game.util;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class SpriteLoader {

    public static ArrayList<Image> loadImages(String filePath) {
        ArrayList<Image> frames = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String filename = String.format("/textures/" + filePath + "%02d.png",  i);
            Image image = new Image(filename);
            frames.add(image);
        }
        return frames;
    }
}
