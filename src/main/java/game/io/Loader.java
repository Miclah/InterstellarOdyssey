package game.io;

import game.entity.RelationshipType;
import javafx.scene.image.Image;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Loader class which loads images from files
 */
public class Loader {

    /**
     * Load images array list.
     *
     * @param filePath the file path
     * @return ArrayList of images
     */
    public static ArrayList<Image> loadImages(String filePath) {
        ArrayList<Image> frames = new ArrayList<> ();
        for (int i = 0; i < 12; i++) {
            String filename = String.format ("/textures/" + filePath + "%02d.png", i);
            Image image = new Image (filename);
            frames.add (image);
        }
        return frames;
    }

    /**
     * Load images array list.
     *
     * @param filePath       the file path
     * @param numberOfImages the number of images
     * @return ArrayList of images
     */
    public static ArrayList<Image> loadImages(String filePath, int numberOfImages) {
        ArrayList<Image> frames = new ArrayList<> ();
        for (int i = 0; i < numberOfImages; i++) {
            String filename = String.format ("/textures/" + filePath + "%02d.png", i);
            Image image = new Image (filename);
            frames.add (image);
        }
        return frames;
    }

    /**
     * Load dialogues hash map.
     *
     * @param filePath the file path
     * @param npcType  the npc type
     * @return the hash map of dialogues specified with relationtype and the actual dialog stores inside an arraylist
     */
    public static HashMap<RelationshipType, ArrayList<String>> loadDialogues(String filePath, String npcType) {
        try {
            String fileContent = Files.readString(Paths.get(filePath));
            JSONObject jsonData = new JSONObject(fileContent);
            JSONObject npcData = jsonData.getJSONObject(npcType.toUpperCase());

            HashMap<RelationshipType, ArrayList<String>> dialogues = new HashMap<>();
            for (RelationshipType relationshipType : RelationshipType.values()) {
                JSONArray lines = npcData.getJSONArray(relationshipType.name());
                ArrayList<String> dialogueLines = new ArrayList<>();
                for (int j = 0; j < lines.length(); j++) {
                    dialogueLines.add(lines.getString(j));
                }
                dialogues.put(relationshipType, dialogueLines);
            }
            return dialogues;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Loads names from a binary file
     *
     * @param filePath the file path
     * @return An arralist of String names
     */
    public static ArrayList<String> loadNames(String filePath) {
        ArrayList<String> names = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream (new FileInputStream (filePath))) {
            int count = dis.readInt();
            for (int i = 0; i < count; i++) {
                String name = dis.readUTF();
                names.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }
}
