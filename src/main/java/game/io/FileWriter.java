package game.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public static void writeNamesToFile(String fileName, String[] names) {
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IllegalStateException("Could not create directory: " + parentDir);
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(names.length);
            for (String name : names) {
                dos.writeUTF(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
