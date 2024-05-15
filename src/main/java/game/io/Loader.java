package game.io;

import game.entity.RelationshipType;
import javafx.scene.image.Image;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class Loader {

    public static ArrayList<Image> loadImages(String filePath) {
        ArrayList<Image> frames = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String filename = String.format("/textures/" + filePath + "%02d.png",  i);
            Image image = new Image(filename);
            frames.add(image);
        }
        return frames;
    }

    public static HashMap<RelationshipType, ArrayList<String>> loadDialogues(String filePath, String npcType) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonData = new JSONObject(reader);
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
}
