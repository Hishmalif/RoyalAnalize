package userFileManager;

import interaction.UserInteraction;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class FilesReader extends WriteReader {
    Path path = ConfigReader.getConfigData();

    /**
     * Static method for read config
     * Return data from logs game
     */
    public static String getFile() {
        FilesReader fr = new FilesReader();
        try (Stream<Path> stream = Files.list(fr.path)) {
            Map<Byte, Path> mapFiles = new HashMap<>();
            List<Path> files = stream
                    .filter(path -> !Files.isDirectory(path))
                    .limit(15)
                    .collect(Collectors.toList());

            for (int i = 1; i < files.size() + 1; i++) {
                mapFiles.put((byte) i, files.get(i - 1));
            }
            Path userChoice = UserInteraction.choiceFile(mapFiles);
            if (userChoice == null) {
                return "";
            }
            return fr.reader(userChoice);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for read colors from chat
     */
    public static List<String> getGameConfig() {
        FilesReader fr = new FilesReader();
        List<String> result = new ArrayList<>();

        Path conf = Paths.get(fr.path.getParent().toString() + "\\" + "config.txt");
        String[] strings = fr.reader(conf).split(System.lineSeparator());

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains("colors")) {
                for (int j = i + 1; j < strings.length; j++) {
                    if (strings[j].contains("}")) {
                        break;
                    }
                    result.add(strings[j].trim());
                }
                break;
            }
        }
        return result;
    }
}