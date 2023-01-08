package userFileManager;

import interaction.UserInteraction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigReader extends WriteReader {
    final Path path = Paths.get("config.cfg"); // Config filename
    /**
     * This method check user configuration.
     * If file not exists in default path create him
     */
    private void checkConfigFile(Path pathToConfig) {
        if (Files.notExists(pathToConfig)) {
            try {
                Files.createFile(pathToConfig);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set style for config
     */
    private String checkConfig(Path pathToFile) {
        String data = reader(pathToFile);

        if (data.trim().equals("")) {
            data = UserInteraction.getPathToLogsGame();
            data = String.format("path=%s", data);
            writer(data, pathToFile);
        }
        return data;
    }

    /**
     * Wait param path for check and read
     * if file not exists create him
     * else read data from file
     */

    public static Path getConfigData() {
        ConfigReader cr = new ConfigReader();
        cr.checkConfigFile(cr.path);
        return Paths.get(cr.checkConfig(cr.path).trim().split("=")[1]);
    }
}