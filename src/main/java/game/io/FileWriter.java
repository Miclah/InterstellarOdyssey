package game.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public static void write() {
        String[] maleNames = {
                "Aragorn", "Thorin", "Sauron", "Boromir", "Faramir", "Eomer", "Elrond", "Gimli",
                "Haldir", "Celeborn", "Glorfindel", "Beren", "Fingolfin", "Finrod", "Maedhros",
                "Feanor", "Turgon", "Tuor", "Hurin", "Beleg", "Turin", "Gwindor", "Ecthelion",
                "Gil-galad", "Cirdan", "Voronwe", "Eol", "Maeglin", "Haldir"
        };

        String[] femaleNames = {
                "Eowyn", "Galadriel", "Arwen", "Luthien", "Melian", "Idril", "Nimloth", "Finduilas",
                "Morwen", "Aredhel", "Haleth", "Elwing", "Nienor", "Ancalime", "Rosie", "Nessa",
                "Varda", "Yavanna", "Este", "Vaire", "Vana", "Nienna", "Lorien", "Melian", "Miriel",
                "Indis", "Nerwen", "Anaire", "Amarie", "Elbereth"
        };

        writeNamesToFile("src/main/resources/textures/files/male_names.bin", maleNames);
        writeNamesToFile("src/main/resources/textures/files/female_names.bin", femaleNames);
    }

    private static void writeNamesToFile(String fileName, String[] names) {
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IllegalStateException("Could not create directory: " + parentDir);
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            for (String name : names) {
                dos.writeUTF(name);
            }
            System.out.println("Writing names to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
